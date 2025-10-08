package org.modules.SystemDesign.BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    private Map<City, List<Movie>> cityMovieMap;
    private List<Movie> movieList;

    public MovieController() {
        this.cityMovieMap = new HashMap<>();
        this.movieList = new ArrayList<>();
    }

    public List<Movie> getMovieByCity(City city) {
        return cityMovieMap.getOrDefault(city, new ArrayList<>());
    }

    public void addMovieInCityMovieMap(City city, Movie movie) {
        List<Movie> movieList = cityMovieMap.getOrDefault(city, new ArrayList<>());
        movieList.add(movie);
        cityMovieMap.put(city, movieList);
    }

    public void addMovieInMovieList(Movie movie) {
        this.movieList.add(movie);
    }

    public Movie getMovieByName(String name){
        for(Movie m: movieList){
            if(m.getMovieName().equals(name)){
                return m;
            }
        }
        System.out.println("Movie not found with name " + name);
        return null;
    }

}
