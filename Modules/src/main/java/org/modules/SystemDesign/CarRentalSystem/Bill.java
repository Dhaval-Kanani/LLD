package org.modules.SystemDesign.CarRentalSystem;

import org.modules.SystemDesign.CarRentalSystem.enums.PaymentType;

public class Bill {
    private String billId;
    private Double billAmount;
    private PaymentType paymentType;

    public Bill(String billId, Double billAmount, PaymentType paymentType) {
        this.billId = billId;
        this.billAmount = billAmount;
        this.paymentType = paymentType;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Double getBillAmount() {
        return billAmount;
    }
}
