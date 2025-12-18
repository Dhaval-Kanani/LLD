package org.modules.SystemDesign.VehicleRentalSystemv2.NotificationService;

import org.modules.SystemDesign.VehicleRentalSystemv2.Reservation;
import org.modules.SystemDesign.VehicleRentalSystemv2.paymentService.PaymentResult;

public class NotificationService {

    public void notifyReservationConfirmed(String userId, String reservationId) {
        String message = "Your reservation " + reservationId + " has been confirmed.";
        sendNotification(userId, message);
    }

    public void notifyBookingCompleted(String userId, String reservationId) {
        String message = "Your booking for reservation " + reservationId + " is completed. Thank you for using our service!";
        sendNotification(userId, message);
    }

    public void notifyPaymentDue(String userId, String reservationId, Double amount) {
        String message = "Payment of $" + amount + " is due for your reservation " + reservationId + ". Please make the payment at your earliest convenience.";
        sendNotification(userId, message);
    }

    public void notifyRefundProcessed(String userId, String reservationId, Double amount) {
        String message = "Your refund of $" + amount + " for reservation " + reservationId + " has been processed.";
        sendNotification(userId, message);
    }

    private void sendNotification(String userId, String message) {
        // Logic to send notification (e.g., email, SMS)
        System.out.println("Notification sent to User " + userId + ": " + message);
    }
}
