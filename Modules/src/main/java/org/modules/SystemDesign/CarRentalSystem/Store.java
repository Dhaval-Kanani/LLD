package org.modules.SystemDesign.CarRentalSystem;

import org.modules.SystemDesign.CarRentalSystem.enums.Location;
import org.modules.SystemDesign.CarRentalSystem.enums.PaymentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private String storeId;
    private Location location;
    private VehicleManagementSystem vehicleManagementSystem;
    private Map<String, Reservation> activeReservations;
    private AtomicInteger reservationNo;

    public Store(String storeId, Location location) {
        this.storeId = storeId;
        this.location = location;
        this.vehicleManagementSystem = new VehicleManagementSystem();
        this.activeReservations = new HashMap<>();
        this.reservationNo = new AtomicInteger(0);
    }

    public void addVehicles(List<Vehicle> vehicleList){
        vehicleManagementSystem.setVehicleList(vehicleList);
    }

    public Vehicle getAvailableVehicle(){
        return vehicleManagementSystem.findAvailableVehicle();
    }

    public Reservation reserverVehicle(User user, Vehicle vehicle){
        vehicleManagementSystem.bookVehicle(user, vehicle);
        Reservation reservation = new Reservation(reservationNo.toString(), vehicle);
        activeReservations.putIfAbsent(vehicle.getVehicleId(), reservation);
        reservationNo.incrementAndGet();
        System.out.println("Reserved...");
        return reservation;
    }

    public void pickVehicle(Vehicle vehicle){
        vehicleManagementSystem.pickVehicle(vehicle);
    }

    public Bill completeReservation(Vehicle vehicle, PaymentType paymentType){
        if(!activeReservations.containsKey(vehicle.getVehicleId())){
            System.out.println("Reservation not exist");
            return null;
        }

        vehicleManagementSystem.closeBooking(vehicle);
        Reservation reservation = activeReservations.get(vehicle.getVehicleId());
        double charge = FeeCalculator.calculateCharge(vehicle);
        Bill bill = reservation.completePayment(charge, paymentType);

        activeReservations.remove(vehicle.getVehicleId());

        System.out.println("Booking Closed");

        return bill;
    }
}
