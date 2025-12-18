package org.modules.SystemDesign.BookMyShow2.factory;

import org.modules.SystemDesign.BookMyShow2.Movie;
import org.modules.SystemDesign.BookMyShow2.MovieEnum;

public class MovieFactory {
    public static Movie getMovie(MovieEnum movieName, long duration){
        return new Movie(movieName, duration);
    }
}
