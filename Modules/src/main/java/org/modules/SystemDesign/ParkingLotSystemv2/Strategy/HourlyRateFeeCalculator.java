package org.modules.SystemDesign.ParkingLotSystemv2.Strategy;

import org.modules.SystemDesign.ParkingLotSystemv2.Ticket;

public class HourlyRateFeeCalculator implements FeeStrategy{
    private static final double BIKEFEE = 2.0;
    private static final double CARFEE = 4.0;
    private static final double TRUCKFEE = 5.0;
    private static final double DEFAULTFEE = 2.0;

    @Override
    public Double calculateFee(Ticket ticket) {
        double charge = 0.0;
        switch (ticket.getVehicle().getVehicleType()){
            case BIKE -> charge = BIKEFEE*((ticket.getParkingEndTime() - ticket.getParkingStartTime())/(1000.0*60*60));
            case CAR -> charge =  CARFEE*((ticket.getParkingEndTime() - ticket.getParkingStartTime())/(1000.0*60*60));
            case TRUCK -> charge = TRUCKFEE*((ticket.getParkingEndTime() - ticket.getParkingStartTime())/(1000.0*60*60));
        }
        charge = Math.max(charge, DEFAULTFEE);
        ticket.setCharge(charge);
        return charge;
    }
}
