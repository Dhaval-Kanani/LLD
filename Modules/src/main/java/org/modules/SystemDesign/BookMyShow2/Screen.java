package org.modules.SystemDesign.BookMyShow2;

public class Screen {
    private String screenId;
    private Theater theater;

    public Screen(String  screenId, Theater theater) {
        this.screenId = screenId;
        this.theater = theater;
    }

    public String getScreenId() {
        return screenId;
    }

    public Theater getTheater() {
        return theater;
    }
}
