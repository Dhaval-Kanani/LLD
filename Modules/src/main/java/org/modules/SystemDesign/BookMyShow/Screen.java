package org.modules.SystemDesign.BookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String screenId;
    private List<Seat> seats;

    public Screen(String screenId) {
        this.screenId = screenId;
        this.seats = new ArrayList<>();
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
