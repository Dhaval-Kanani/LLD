package org.modules.SystemDesign.BookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String theaterId;
    private List<Screen> screens;
    private List<Show> shows;
    private City city;

    public Theater(String theaterId, List<Screen> screens, List<Show> shows) {
        this.theaterId = theaterId;
        this.screens = screens;
        this.shows = shows;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public List<Show> getShows() {
        return shows;
    }

    public City getCity() {
        return city;
    }
}
