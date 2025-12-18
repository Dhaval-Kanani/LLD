package org.modules.SystemDesign.BookMyShow2.seats;

public class SilverSeat implements Seat {
    private int seatNo;
    private int price;
    private Boolean isAvailable;

    public SilverSeat(int seatNo, int price) {
        this.seatNo = seatNo;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public int getSeatNo() {
        return seatNo;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public SeatType getSeatType() {
        return SeatType.SILVER;
    }

    @Override
    public Boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
