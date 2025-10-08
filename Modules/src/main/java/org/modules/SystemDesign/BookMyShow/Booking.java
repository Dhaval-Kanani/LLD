package org.modules.SystemDesign.BookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String bookingId;
    private PaymentType paymentType;
    private Movie movie;
    private Show show;
    private List<Seat> bookedSeats;
    private City city;

    public Booking(Movie movie, PaymentType paymentType, Show show, City city, String bookingId) {
        this.movie = movie;
        this.paymentType = paymentType;
        this.show = show;
        this.city = city;
        this.bookingId = bookingId;
        this.bookedSeats = new ArrayList<>();
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats.addAll(bookedSeats);
    }

    public String getBookingId() {
        return bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public City getCity() {
        return city;
    }
}
