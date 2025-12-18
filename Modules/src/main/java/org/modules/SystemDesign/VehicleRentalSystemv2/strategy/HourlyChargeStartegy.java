package org.modules.SystemDesign.VehicleRentalSystemv2.strategy;

import org.modules.SystemDesign.VehicleRentalSystemv2.Reservation;

public class HourlyChargeStartegy implements FeeStrategy {
    private static final Double BIKERATE = 2.0;
    private static final Double CARRATE = 4.0;
    private static final Double SCOTTYRate = 1.5;
    @Override
    public Double calculateCharge(Reservation reservation) {
        double charge = 0.0;
        switch (reservation.getVehicle().getVehicleType()){
            case CAR -> charge = CARRATE*((reservation.getBookingEndTime() - reservation.getBookingStartTime())/(1000.0*60*60));
            case BIKE -> charge = BIKERATE*((reservation.getBookingEndTime() - reservation.getBookingStartTime())/(1000.0*60*60));
            case SCOTTY -> charge = SCOTTYRate*((reservation.getBookingEndTime() - reservation.getBookingStartTime())/(1000.0*60*60));
        }
        reservation.setCharge(charge);
        return charge;
    }
}
