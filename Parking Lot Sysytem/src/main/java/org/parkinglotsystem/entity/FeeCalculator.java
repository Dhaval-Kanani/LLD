package org.parkinglotsystem.entity;

import org.parkinglotsystem.enums.VehicleType;

public class FeeCalculator {
    private final static double BikeFee = 3.0;
    private final static double CarFee = 4.0;
    private final static double TruckFee = 6.0;
    private final static double DefaultFee = 2.0;

    public static double calculateFee(double totalHrs, VehicleType vehicleType){
        double calculatedFare = 0.0;
        switch (vehicleType){
            case TRUCK -> calculatedFare = TruckFee*totalHrs;
            case CAR -> calculatedFare =  CarFee*totalHrs;
            case BIKE -> calculatedFare = BikeFee*totalHrs;
            default -> calculatedFare =  2.0;
        }
        return Math.max(calculatedFare, DefaultFee);
    }
}
