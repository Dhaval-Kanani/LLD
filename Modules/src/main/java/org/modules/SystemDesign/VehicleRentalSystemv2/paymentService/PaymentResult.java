package org.modules.SystemDesign.VehicleRentalSystemv2.paymentService;

public class PaymentResult {
    private PaymentStatus status;
    private double amount;
    private String message;

    public PaymentResult(PaymentStatus status, double amount, String message) {
        this.status = status;
        this.amount = amount;
        this.message = message;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }
}
