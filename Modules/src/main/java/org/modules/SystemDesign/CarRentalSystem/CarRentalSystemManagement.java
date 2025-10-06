package org.modules.SystemDesign.CarRentalSystem;

import org.modules.SystemDesign.CarRentalSystem.enums.Location;
import org.modules.SystemDesign.CarRentalSystem.enums.PaymentType;
import org.modules.SystemDesign.CarRentalSystem.enums.VehicleType;

import java.util.List;

public class CarRentalSystemManagement {
    public static void main(String[] args) {
        List<Store> storeList;

        User user1 = new User("U01", "DL01");
        User user2 = new User("U02", "DL02");
        User user3 = new User("U03", "DL03");

        Store store1 = new Store("S01", Location.BANGLORE);
        Store store2 = new Store("S02", Location.BANGLORE);
        Store store3 = new Store("S03", Location.BANGLORE);

        Vehicle vehicle1 = new Vehicle("V01", VehicleType.CAR, "P01");
        Vehicle vehicle2 = new Vehicle("V02", VehicleType.CAR, "P02");
        Vehicle vehicle3 = new Vehicle("V03", VehicleType.CAR, "P03");
        Vehicle vehicle4 = new Vehicle("V04", VehicleType.CAR, "P04");
        Vehicle vehicle5 = new Vehicle("V05", VehicleType.CAR, "P05");

        store1.addVehicles(List.of(vehicle1, vehicle2));
        store2.addVehicles(List.of(vehicle3, vehicle4));
        store3.addVehicles(List.of(vehicle5));

        Vehicle vehicle = store1.getAvailableVehicle();

        Reservation reservation1 = store1.reserverVehicle(user1, vehicle);
        Bill bill1 = store1.completeReservation(vehicle, PaymentType.CASH);

        System.out.println("Bill amount: "  + bill1.getBillAmount());
    }

}
