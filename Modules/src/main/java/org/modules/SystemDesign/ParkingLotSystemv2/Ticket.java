package org.modules.SystemDesign.ParkingLotSystemv2;


public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private Long parkingStartTime;
    private Long parkingEndTime;
    private Double charge;
    private Boolean isPaid;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.parkingStartTime = System.currentTimeMillis();
        this.parkingEndTime = 0L;
        this.charge = 0.0;
        this.isPaid = false;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(Long parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public Long getParkingEndTime() {
        return parkingEndTime;
    }

    public void setParkingEndTime(Long parkingEndTime) {
        this.parkingEndTime = parkingEndTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getTicketId() {
        return ticketId;
    }
}
