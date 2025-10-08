package org.modules.SystemDesign.BookMyShow;

public class Seat {
    private Integer seatId;
    private Integer row;
    private SeatType seatType;

    public Seat(Integer seatId, int row, SeatType seatType) {
        this.seatId = seatId;
        this.row = row;
        this.seatType = seatType;
    }

    public Integer getSeatId() {
        return seatId;
    }
}
