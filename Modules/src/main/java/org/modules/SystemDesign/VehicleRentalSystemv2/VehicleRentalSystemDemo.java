package org.modules.SystemDesign.VehicleRentalSystemv2;

import org.modules.SystemDesign.VehicleRentalSystemv2.strategy.HourlyChargeStartegy;

public class VehicleRentalSystemDemo {
    public static void main(String[] args) {
        VehicleRentalSystemController controller = VehicleRentalSystemController.getInstance();

        // Add a store
        String storeId = "store1";
        controller.addStore(storeId, City.BANGALORE, new HourlyChargeStartegy());

        // Add a user
        String userId = "user1";
        User user = new User(userId);

        // Add vehicles to the store
        Store store = controller.getStore(storeId);
        Vehicle car = new Vehicle("car1", VehicleType.CAR);
        car.setAvailable(true);
        store.getAvailableVehicle(VehicleType.CAR); // Just to show usage, not needed here
        store.addVehicle(car);

        // Reserve a vehicle
        Reservation reservation = controller.reserveVehicle(storeId, VehicleType.CAR, 10.0, user);

        // Complete booking (simulate return)
        controller.completeBooking(storeId, reservation, user);
    }
}
