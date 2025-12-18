package org.modules.SystemDesign.VehicleRentalSystemv2;

import org.modules.SystemDesign.VehicleRentalSystemv2.strategy.FeeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VehicleRentalSystemController {
    private static VehicleRentalSystemController vehicleRentalSystemController;
    private static Object lock = new Object();
    private Map<String, Store> stores;

    private VehicleRentalSystemController() {
        stores = new HashMap<>();
    }

    public static VehicleRentalSystemController getInstance() {
        if (vehicleRentalSystemController == null) {
            synchronized (lock){
                if(vehicleRentalSystemController==null){
                    vehicleRentalSystemController = new VehicleRentalSystemController();
                }
            }
        }
        return vehicleRentalSystemController;
    }

    public void addStore(String storeId, City city, FeeStrategy feeStrategy) {
        stores.put(storeId, new Store(storeId, city, feeStrategy));
    }

    public Store getStore(String storeId) {
        return stores.get(storeId);
    }

    // Example: Reserve a vehicle
    public Reservation reserveVehicle(String storeId, VehicleType vehicleType, double amountPaid, User user) {
        Store store = stores.get(storeId);
        if (store == null) {
            System.out.println("Store not found.");
            return null;
        }
        Vehicle vehicle = store.getAvailableVehicle(vehicleType);
        if (vehicle != null) {
            return store.reserveVehicle(vehicle, amountPaid, user);
        }
        return null;
    }

    // Example: Complete booking
    public void completeBooking(String storeId, Reservation reservation, User user) {
        Store store = stores.get(storeId);
        if (store == null) {
            System.out.println("Store not found.");
            return;
        }

        store.completeBooking(reservation.getVehicle(), user);
    }
}
