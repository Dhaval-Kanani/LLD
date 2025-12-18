package org.modules.SystemDesign.BookMyShow2;

import org.modules.SystemDesign.BookMyShow2.seats.Seat;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {
    private static BookingService instance;
    private static final Object lock = new Object();
    private final ConcurrentHashMap<String, ReentrantLock> showLocks;

    private BookingService() {
        this.showLocks = new ConcurrentHashMap<>();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public Ticket bookSeats(User user, Show show, List<Seat> requestedSeats) {
        String showId = show.getMovie().getMovieName().toString() + "_" + show.getStartTime();
        ReentrantLock showLock = showLocks.computeIfAbsent(showId, k -> new ReentrantLock());

        showLock.lock();
        try {
            // Check if all requested seats are available
            for (Seat seat : requestedSeats) {
                if (!isSeatAvailable(show, seat.getSeatNo())) {
                    System.out.println("Seat " + seat.getSeatNo() + " is not available!");
                    return null;
                }
            }

            // Mark seats as booked
            for (Seat requestedSeat : requestedSeats) {
                for (Seat showSeat : show.getSeatList()) {
                    if (showSeat.getSeatNo() == requestedSeat.getSeatNo()) {
                        showSeat.setAvailable(false);
                        break;
                    }
                }
            }

            // Calculate total price
            int totalPrice = requestedSeats.stream().mapToInt(Seat::getPrice).sum();

            // Create and return ticket
            return new Ticket(UUID.randomUUID().toString(), show, totalPrice, requestedSeats);

        } finally {
            showLock.unlock();
        }
    }

    private boolean isSeatAvailable(Show show, int seatNo) {
        for (Seat seat : show.getSeatList()) {
            if (seat.getSeatNo() == seatNo) {
                return seat.isAvailable();
            }
        }
        return false;
    }

    public List<Seat> getAvailableSeats(Show show) {
        return show.getSeatList().stream()
                .filter(Seat::isAvailable)
                .toList();
    }
}
