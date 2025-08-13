package org.parkinglotsystem.entity;

import org.parkinglotsystem.enums.PaymentType;

public class Ticket {
    private String ticketId;
    private String vehicleId;
    private String parkingSpotId;
    private long entryTime;
    private long exitTime;
    private double charge;
    private PaymentType paymentType;
    private Boolean isPaid;

    public Ticket(String ticketId, String vehicleId, String parkingSpotId) {
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.parkingSpotId = parkingSpotId;
        this.entryTime = System.currentTimeMillis();
        this.exitTime = 0;
        this.isPaid = false;
    }

    public double calculateHours(){
        long endTime = this.exitTime > 0 ? this.exitTime : System.currentTimeMillis();
        long totalTime = endTime - entryTime;
        return totalTime/(1000.0*60.0*60.0);
    }

    public Boolean makePayment(PaymentType paymentType, double amountPaid){
        if(amountPaid >= this.charge && !isPaid){
            this.isPaid = true;
            this.paymentType = paymentType;
            return true;
        }
        return false;
    }

    public void exitVehicle(double calculatedFee){
        this.charge = calculatedFee;
        this.exitTime = System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(String parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
