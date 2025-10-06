package org.modules.SystemDesign.CarRentalSystem;

import org.modules.SystemDesign.CarRentalSystem.enums.VehicleType;

import java.time.LocalDateTime;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private String plateNo;
    private Boolean isAvailable;
    private User user;
    private LocalDateTime bookingDate;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long startDuration;
    private long endDuration;

    public Vehicle(String vehicleId, VehicleType vehicleType, String plateNo) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.plateNo = plateNo;
        this.isAvailable = true;
        this.user = null;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public long getStartDuration() {
        return startDuration;
    }

    public void setStartDuration(long startDuration) {
        this.startDuration = startDuration;
    }

    public long getEndDuration() {
        return endDuration;
    }

    public void setEndDuration(long endDuration) {
        this.endDuration = endDuration;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
