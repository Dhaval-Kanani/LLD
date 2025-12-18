package org.modules.SystemDesign.BookMyShow2;

import org.modules.SystemDesign.BookMyShow2.seats.Seat;

import java.util.List;

public class Show {
    private String showId;
    private Movie movie;
    private List<Seat> seatList;
    private Theater theater;
    private Screen screen;
    private long startTime;

    public Show(String showId, Movie movie, List<Seat> seatList, Theater theater, Screen screen, long startTime) {
        this.showId = showId;
        this.movie = movie;
        this.seatList = seatList;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public Screen getScreen() {
        return screen;
    }

    public long getStartTime() {
        return startTime;
    }
}
