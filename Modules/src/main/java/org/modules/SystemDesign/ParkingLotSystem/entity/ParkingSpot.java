package org.modules.SystemDesign.ParkingLotSystem.entity;


import org.modules.SystemDesign.ParkingLotSystem.enums.ParkingSpotType;
import org.modules.SystemDesign.ParkingLotSystem.enums.VehicleType;

public class ParkingSpot {
    private String parkingSpotId;
    private ParkingSpotType parkingSpotType;
    private int floor;
    private int spot;
    private Boolean isAvailable;
    private Vehicle vehicle;

    private final Object object = new Object();

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType, int floor, int spot) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
        this.floor = floor;
        this.spot = spot;
        this.isAvailable = true;
    }

    public Boolean parkVehicle(Vehicle vehicle){
        synchronized (object) {
            if (isAvailable && canAccommodate(vehicle.getVehicleType())) {
                isAvailable = false;
                this.vehicle = vehicle;
                return true;
            }
            return false;
        }
    }

    public Boolean canAccommodate(VehicleType vehicleType){
        synchronized (object) {
            return switch (this.parkingSpotType) {
                case LARGE -> true;
                case REGULAR -> vehicleType == VehicleType.BIKE || vehicleType == VehicleType.CAR;
                case COMPACT -> vehicleType == VehicleType.BIKE;
                default -> false;
            };
        }
    }

    public void unParkVehicle(){
        synchronized (object) {
            if (!isAvailable) {
                Vehicle vehicle = this.vehicle;
                isAvailable = true;
                this.vehicle = null;
            }
        }
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(String parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public Boolean getAvailable() {
        synchronized (object) {
            return this.isAvailable;
        }
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
