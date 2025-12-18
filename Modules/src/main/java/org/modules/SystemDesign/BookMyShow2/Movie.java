package org.modules.SystemDesign.BookMyShow2;

public class Movie {
    private MovieEnum movieName;
    private long duration;

    public Movie(MovieEnum movieName, long duration) {
        this.movieName = movieName;
        this.duration = duration;
    }

    public MovieEnum getMovieName() {
        return movieName;
    }

    public long getDuration() {
        return duration;
    }
}
