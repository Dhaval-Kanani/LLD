package org.modules.SystemDesign.ParkingLotSystemv2;

import org.modules.SystemDesign.ParkingLotSystemv2.enums.ParkingSize;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.VehicleType;

public class ParkingSpot {
    private String parkingSpotId;
    private Boolean isAvailable;
    private ParkingSize parkingSize;

    public ParkingSpot(String parkingSpotId, ParkingSize parkingSize) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSize = parkingSize;
        this.isAvailable = true;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public ParkingSize getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(ParkingSize parkingSize) {
        this.parkingSize = parkingSize;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public Boolean canAccommodate(VehicleType vehicleType){
        return this.isAvailable && canFit(vehicleType);
    }

    private Boolean canFit(VehicleType vehicleType){
        switch (this.parkingSize){
            case LARGE -> {
                return true;
            }
            case MEDIUM -> {
                return vehicleType.equals(VehicleType.BIKE) || vehicleType.equals(VehicleType.CAR);
            }
            case COMPACT -> {
                return vehicleType.equals(VehicleType.BIKE);
            }
            default -> {
                return false;
            }
        }
    }
}
