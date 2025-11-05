package org.modules.SystemDesign.BookMyShow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookMyShow {
    private MovieController movieController;
    private TheaterController theaterController;
    private AtomicInteger bookingNo;

    public BookMyShow() {
        this.movieController = new MovieController();
        this.theaterController = new TheaterController();
        this.bookingNo = new AtomicInteger(0);
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        bookMyShow.createBooking(City.BANGLORE, "KGF");
        bookMyShow.createBooking(City.BANGLORE, "KGF");
    }

    private void createBooking(City city, String movieName){

        List<Movie> movieList = movieController.getMovieByCity(city);
        Movie intersetedMovie = null;

        for(Movie movie: movieList){
            if(movie.getMovieName().equals(movieName)){
                intersetedMovie = movie;
                break;
            }
        }

        Show show = theaterController.getShow(intersetedMovie, city);

        int interestedSeat = 30;
        List<Integer> bookedSeats = show.getBookedSeats();

        if(!bookedSeats.contains(interestedSeat)){
            bookedSeats.add(interestedSeat);
            Booking booking = new Booking(intersetedMovie, PaymentType.CARD, show, city, bookingNo.toString());
            bookingNo.incrementAndGet();
            List<Seat> myBookedSeats = new ArrayList<>();
            for(Seat seat: show.getScreen().getSeats()){
                if(seat.getSeatId()==interestedSeat){
                    myBookedSeats.add(seat);
                }
            }
            booking.setBookedSeats(myBookedSeats);

            System.out.println("Booking created successfully with id " + booking.getBookingId() + " for movie: " + booking.getMovie().getMovieName()
                + " in city: " + booking.getCity() + " show start time: " + booking.getShow().getStartTime());
            return;
        }

        System.out.println("Failed to book seat. Seat " + interestedSeat + " is unavailable. Try again later!!");

    }

    private void initialize(){
        initializeMovies();
        initializeTheaters();
    }

    private void initializeMovies() {
        Movie movie1 = new Movie("Avengers", 130);
        Movie movie2 = new Movie("Bahubali", 150);
        Movie movie3 = new Movie("KGF", 160);
        List<Movie> movies = List.of(movie1, movie2, movie3);
        Map<City, List<Movie>> delhiMovies = new HashMap<>();
        delhiMovies.put(City.DELHI, movies);
        Map<City, List<Movie>> blrMovies = new HashMap<>();
        blrMovies.put(City.BANGLORE, movies);
        movieController.setCityMovieMap(delhiMovies);
        movieController.setCityMovieMap(blrMovies);
        movieController.setMovieList(movies);
    }

    private void initializeTheaters() {

        List<Seat> seatList = createSeats();

        Screen screen1 = new Screen("S01");
        Screen screen2 = new Screen("S02");
        Screen screen3 = new Screen("S03");
        List<Screen> screens = List.of(screen1, screen2, screen3);

        Movie movie1 = movieController.getMovieByName("Avengers");
        Movie movie2 = movieController.getMovieByName("Bahubali");
        Movie movie3 = movieController.getMovieByName("KGF");

        Show show1 = new Show("S01", screen1, movie1, LocalDateTime.now());
        Show show2 = new Show("S02", screen2, movie2, LocalDateTime.now().plusHours(1));
        Show show3 = new Show("S03", screen3, movie3, LocalDateTime.now().plusHours(2));
        List<Show> shows = List.of(show1, show2, show3);

        theaterController.setCityTheaterMap(City.DELHI, new Theater("T01", screens, shows));

        theaterController.setCityTheaterMap(City.BANGLORE, new Theater("T02", screens, shows));
        theaterController.setCityTheaterMap(City.BANGLORE, new Theater("T03", screens, shows));
    }

    private List<Seat> createSeats(){
        List<Seat> seats  = new ArrayList<>();

        for(int i=0; i<20; i++) {
            Seat seat = new Seat(i+1, i/10 + 1, SeatType.SILVER);
            seats.add(seat);
        }
        for(int i=20; i<30; i++) {
            Seat seat = new Seat(i+1, i/10 + 1, SeatType.GOLD);
            seats.add(seat);
        }
        for(int i=30; i<40; i++) {
            Seat seat = new Seat(i+1, i/10 + 1, SeatType.PLATINUM);
            seats.add(seat);
        }
        return seats;
    }
}
