package org.modules.SystemDesign.ParkingLotSystemv2;

import org.modules.SystemDesign.ParkingLotSystemv2.enums.Floors;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.VehicleType;

import java.util.Map;

public class ParkingFloor {
    private Floors floor;
    private Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(Floors floor) {
        this.floor = floor;
    }

    public Floors getFloor() {
        return floor;
    }

    public void setFloor(Floors floor) {
        this.floor = floor;
    }

    public Map<String, ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(Map<String, ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void addParkingSpots(ParkingSpot parkingSpot) {
        this.parkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
    }

    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType){
        for(String key: parkingSpots.keySet()){
            ParkingSpot parkingSpot = parkingSpots.get(key);
            if(parkingSpot.canAccommodate(vehicleType)) return parkingSpot;
        }
        System.out.println("No Spot available at floor: " + this.floor + " for vehicle type: " + vehicleType);
        return null;
    }
}
