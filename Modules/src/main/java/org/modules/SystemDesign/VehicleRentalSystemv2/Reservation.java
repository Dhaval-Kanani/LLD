package org.modules.SystemDesign.VehicleRentalSystemv2;

import java.time.LocalDateTime;

public class Reservation {
    private String reservationId;
    private Vehicle vehicle;
    private LocalDateTime bookedDate;
    private Long bookingStartTime;
    private Long bookingEndTime;
    private Double charge;
    private Double amountPaid;
    private BookingStatus bookingStatus;

    public Reservation(String reservationId, Vehicle vehicle, Double amountPaid) {
        this.reservationId = reservationId;
        this.vehicle = vehicle;
        this.bookedDate = LocalDateTime.now();
        this.bookingStartTime = System.currentTimeMillis();
        this.bookingEndTime = System.currentTimeMillis();
        this.charge = 0.0;
        this.amountPaid = amountPaid;
        this.bookingStatus = BookingStatus.RESERVED;
    }

    public Long getBookingStartTime() {
        return bookingStartTime;
    }

    public void setBookingStartTime(Long bookingStartTime) {
        this.bookingStartTime = bookingStartTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Long getBookingEndTime() {
        return bookingEndTime;
    }

    public void setBookingEndTime(Long bookingEndTime) {
        this.bookingEndTime = bookingEndTime;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBooking(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
