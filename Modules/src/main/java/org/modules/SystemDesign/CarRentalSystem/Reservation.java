package org.modules.SystemDesign.CarRentalSystem;

import org.modules.SystemDesign.CarRentalSystem.enums.PaymentType;

import java.lang.reflect.AnnotatedType;
import java.util.concurrent.atomic.AtomicInteger;

public class Reservation {
    private String reservationId;
    private Vehicle vehicle;
    private Bill bill;
    private Boolean isPaid;
    private AtomicInteger billNo;


    public Reservation(String reservationId, Vehicle vehicle) {
        this.reservationId = reservationId;
        this.vehicle = vehicle;
        this.bill = null;
        this.isPaid = false;
        this.billNo = new AtomicInteger(0);
    }

    public String getReservationId() {
        return reservationId;
    }

    public Bill completePayment(double charge, PaymentType paymentType){
        this.bill = new Bill(billNo.toString(), charge, paymentType);
        this.isPaid = true;
        return this.bill;
    }
}
