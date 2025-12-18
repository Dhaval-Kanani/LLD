package org.modules.SystemDesign.VehicleRentalSystemv2;

import java.util.HashMap;
import java.util.Map;

public class VehicleManager {
    private String vehicleManagerId;
    private Map<String, Vehicle> vehicleMap;

    public VehicleManager(String vehicleManagerId) {
        this.vehicleManagerId = vehicleManagerId;
        this.vehicleMap = new HashMap<>();
    }

    public Map<String, Vehicle> getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(Map<String, Vehicle> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    public void addVehicleMap(Vehicle vehicle) {
        this.vehicleMap.put(vehicle.getVehicleId(), vehicle);
    }

    public Vehicle findAvailableVehicle(VehicleType vehicleType){
        for(String vehicleId : vehicleMap.keySet()){
            Vehicle vehicle = vehicleMap.get(vehicleId);
            if(vehicle.getVehicleType().equals(vehicleType) && vehicle.getAvailable()){
                return vehicle;
            }
        }
        System.out.println("No available vehicle of type " + vehicleType + " present at the moment");
        return null;
    }

    public void reserveVehicle(Vehicle vehicle){
        vehicle.setAvailable(false);
    }

    public void unReserveVehicle(Vehicle vehicle){
        vehicle.setAvailable(true);
    }

}
