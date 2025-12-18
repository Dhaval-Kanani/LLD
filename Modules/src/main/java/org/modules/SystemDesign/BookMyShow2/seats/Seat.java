package org.modules.SystemDesign.BookMyShow2.seats;

public interface Seat {
    int getSeatNo();
    int getPrice();
    SeatType getSeatType();
    Boolean isAvailable();
    void setAvailable(Boolean isAvailable);
}
