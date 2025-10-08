package org.modules.SystemDesign.BookMyShow;

public class Movie {
    private String movieName;
    private long duration;

    public Movie(String movieName, long duration) {
        this.movieName = movieName;
        this.duration = duration;
    }

    public String getMovieName() {
        return movieName;
    }
}
