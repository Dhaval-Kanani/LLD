package org.modules.SystemDesign.BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterController {
    private Map<City, List<Theater>> cityTheaterMap;

    public TheaterController() {
        this.cityTheaterMap = new HashMap<>();
    }

    public Map<City, List<Theater>> getCityTheaterMap() {
        return cityTheaterMap;
    }

    public void setCityTheaterMap(City city, Theater theater) {
        List<Theater> theaterList = cityTheaterMap.getOrDefault(city, new ArrayList<>());
        theaterList.add(theater);
        cityTheaterMap.put(city, theaterList);
    }

    public Show getShow(Movie movie, City city){
        List<Theater> theaterList = cityTheaterMap.getOrDefault(city, new ArrayList<>());

        if(theaterList.isEmpty()){
            System.out.println("No show is running in the " + city);
            return null;
        }

        for(Theater theater: theaterList){
            List<Show> showList = theater.getShows();
            return showList.get(0);
        }
        System.out.println("Error in finding the show in " + city);
        return null;
    }
}
