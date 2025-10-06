package org.modules.SystemDesign.CarRentalSystem;

public abstract class FeeCalculator {
    private static Double chargePerHour = 2.0;

    public static Double calculateCharge(Vehicle vehicle){
        double duration = (vehicle.getEndDuration() - vehicle.getStartDuration())/(1000.0*60*60);
        return Math.max(Math.round(duration*chargePerHour), chargePerHour);
    }
}
