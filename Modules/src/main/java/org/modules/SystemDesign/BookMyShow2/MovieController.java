package org.modules.SystemDesign.BookMyShow2;

import java.util.*;

public class MovieController {
    private static MovieController movieController;
    private static Object object = new Object();
    private Map<City, Map<Movie, List<Show>>> cityToShowMap;
    private List<Movie> movies;

    public static MovieController getInstance(List<Movie> movies, Map<City, Map<Movie, List<Show>>> cityToShowMap){
        if(movieController == null){
            synchronized (object){
                movieController = new MovieController(movies, cityToShowMap);
            }
        }
        return movieController;
    }

    private MovieController(List<Movie> movies, Map<City, Map<Movie, List<Show>>> cityToShowMap) {
        this.cityToShowMap = cityToShowMap;
        this.movies = movies;
    }

    public Map<City, Map<Movie, List<Show>>> getCityToShowMap() {
        return cityToShowMap;
    }

    public void setCityToShowMap(Map<City, Map<Movie, List<Show>>> cityToShowMap) {
        this.cityToShowMap = cityToShowMap;
    }

    public void addCityToShowMap(City city, Movie movie, Show show) {
        Map<Movie, List<Show>> movies = cityToShowMap.getOrDefault(city, new HashMap<>());
        List<Show> shows = movies.getOrDefault(movie, new ArrayList<>());
        shows.add(show);
        movies.put(movie, shows);
        cityToShowMap.put(city, movies);
        addMovie(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public Show findShow(City city, Movie movie){
        if(!cityToShowMap.containsKey(city)){
            System.out.println("No movie show for the movie: " + movie.getMovieName() + " in the city " + city.name());
            return null;
        }

        Map<Movie, List<Show>> movies = cityToShowMap.get(city);

        if(!movies.containsKey(movie)){
            System.out.println("No movie show for the movie: " + movie.getMovieName() + " in the city " + city.name());
            return null;
        }

        List<Show> showList = movies.get(movie);

        return !showList.isEmpty() ? showList.get(0) :  null;
    }
}
