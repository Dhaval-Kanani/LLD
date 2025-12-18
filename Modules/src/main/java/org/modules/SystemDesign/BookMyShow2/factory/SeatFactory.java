package org.modules.SystemDesign.BookMyShow2.factory;

import org.modules.SystemDesign.BookMyShow2.seats.GoldSeat;
import org.modules.SystemDesign.BookMyShow2.seats.Seat;
import org.modules.SystemDesign.BookMyShow2.seats.SilverSeat;

public class SeatFactory {
    public static Seat getSeat(int seatNo, int price) {
        if(seatNo<=30){
            return new SilverSeat(seatNo, price);
        } else {
            return new GoldSeat(seatNo, price);
        }
    }
}
