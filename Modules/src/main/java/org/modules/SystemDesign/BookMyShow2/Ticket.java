package org.modules.SystemDesign.BookMyShow2;

import org.modules.SystemDesign.BookMyShow2.seats.Seat;

import java.util.List;

public class Ticket {
    private String ticketId;
    private Show show;
    private int price;
    private List<Seat> bookedSeats;

    public Ticket(String ticketId, Show show, int price, List<Seat> bookedSeats) {
        this.ticketId = ticketId;
        this.show = show;
        this.price = price;
        this.bookedSeats = bookedSeats;
    }

    public Show getShow() {
        return show;
    }

    public int getPrice() {
        return price;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }
}
