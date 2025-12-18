package org.modules.SystemDesign.BookMyShow2;

public class Theater {
    private String theaterId;
    private City city;

    public Theater(String theaterId, City city) {
        this.theaterId = theaterId;
        this.city = city;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public City getCity() {
        return city;
    }
}
