package org.modules.SystemDesign.CarRentalSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementSystem {
    private List<Vehicle> vehicleList;

    public VehicleManagementSystem() {
        this.vehicleList = new ArrayList<>();
    }

    public Vehicle findAvailableVehicle(){
        for(Vehicle v: vehicleList){
            if(v.getAvailable()) return v;
        }
        return null;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList.addAll(vehicleList);
    }

    public void bookVehicle(User user, Vehicle vehicle){
        vehicle.setUser(user);
        vehicle.setAvailable(false);
        vehicle.setBookingDate(LocalDateTime.now());
    }

    public void pickVehicle(Vehicle vehicle){
        vehicle.setStartDateTime(LocalDateTime.now());
        vehicle.setStartDuration(System.currentTimeMillis());
    }

    public void closeBooking(Vehicle vehicle){
        vehicle.setUser(null);
        vehicle.setAvailable(false);
        vehicle.setEndDateTime(LocalDateTime.now().plusDays(2));
        vehicle.setEndDuration(vehicle.getStartDuration() + 300*60*1000);
    }
}
