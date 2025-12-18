package org.modules.SystemDesign.VehicleRentalSystemv2.paymentService;

import org.modules.SystemDesign.VehicleRentalSystemv2.BookingStatus;
import org.modules.SystemDesign.VehicleRentalSystemv2.Reservation;

public class PaymentService {
    public PaymentResult processPayment(Reservation reservation, double charge){
        double amountPaid = reservation.getAmountPaid();

        if(amountPaid < charge){
            return new PaymentResult(PaymentStatus.INSUFFICIENT, charge - amountPaid, "Pay due amount: " + (charge - amountPaid));
        } else if(amountPaid > charge){
            return new PaymentResult(PaymentStatus.REFUND_DUE, amountPaid - charge, "Refund due: " + (amountPaid - charge));
        } else{
            return new PaymentResult(PaymentStatus.COMPLETED, 0, "Payment completed successfully.");
        }
    }
}
