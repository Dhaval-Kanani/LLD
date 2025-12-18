package org.modules.SystemDesign.ParkingLotSystemv2;

import org.modules.SystemDesign.ParkingLotSystemv2.enums.VehicleType;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private String plateNo;
    private Boolean isParked;

    public Vehicle(String vehicleId, String plateNo, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.plateNo = plateNo;
        this.isParked = false;
    }

    public Boolean getParked() {
        return isParked;
    }

    public void setParked(Boolean parked) {
        isParked = parked;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
