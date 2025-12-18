package org.modules.SystemDesign.VehicleRentalSystemv2.strategy;

import org.modules.SystemDesign.VehicleRentalSystemv2.Reservation;

public interface FeeStrategy {
    Double calculateCharge(Reservation reservation);
}
