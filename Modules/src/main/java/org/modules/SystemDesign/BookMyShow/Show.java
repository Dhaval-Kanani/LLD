package org.modules.SystemDesign.BookMyShow;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private String showId;
    private Screen screen;
    private Movie movie;
    private LocalDateTime startTime;
    private List<Integer> bookedSeats;

    public Show(String showId, Screen screen, Movie movie, LocalDateTime startTime) {
        this.showId = showId;
        this.screen = screen;
        this.movie = movie;
        this.startTime = startTime;
        this.bookedSeats = new ArrayList<>();
    }

    public void addBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats.addAll(bookedSeats);
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
