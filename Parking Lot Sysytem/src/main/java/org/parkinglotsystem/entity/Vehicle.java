package org.parkinglotsystem.entity;

import org.parkinglotsystem.enums.VehicleType;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private String plateNumber;
    private long entryTime;
    private long exitTime;
    private Boolean isParked;
    private ParkingSpot assignedSpot;

    public Vehicle(String vehicleId, VehicleType vehicleType, String plateNumber) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.plateNumber = plateNumber;
        this.entryTime = 0;
        this.exitTime = 0;
        this.isParked = false;
    }

    public Boolean parkVehicle(ParkingSpot assignedSpot){
        if(!isParked){
            isParked = true;
            this.assignedSpot = assignedSpot;
            entryTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public void unParkVehicle(){
        this.isParked = false;
        this.assignedSpot = null;
        this.exitTime = System.currentTimeMillis();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public Boolean getParked() {
        return isParked;
    }

    public void setParked(Boolean parked) {
        isParked = parked;
    }

    public ParkingSpot getAssignedSpot() {
        return assignedSpot;
    }

    public void setAssignedSpot(ParkingSpot assignedSpot) {
        this.assignedSpot = assignedSpot;
    }
}
