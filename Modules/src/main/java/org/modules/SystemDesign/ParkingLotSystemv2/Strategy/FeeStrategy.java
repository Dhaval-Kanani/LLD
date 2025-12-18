package org.modules.SystemDesign.ParkingLotSystemv2.Strategy;

import org.modules.SystemDesign.ParkingLotSystemv2.Ticket;

public interface FeeStrategy {
    Double calculateFee(Ticket ticket);
}
