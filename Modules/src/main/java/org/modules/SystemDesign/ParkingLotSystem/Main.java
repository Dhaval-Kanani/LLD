package org.modules.SystemDesign.ParkingLotSystem;


import org.modules.SystemDesign.ParkingLotSystem.entity.ParkingSpot;
import org.modules.SystemDesign.ParkingLotSystem.entity.Ticket;
import org.modules.SystemDesign.ParkingLotSystem.entity.Vehicle;
import org.modules.SystemDesign.ParkingLotSystem.enums.ParkingSpotType;
import org.modules.SystemDesign.ParkingLotSystem.enums.VehicleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ParkingLotManager manager = new ParkingLotManager();

//        manager.addParkingSpot(new ParkingSpot("P01", ParkingSpotType.REGULAR, 1, 1));
        for (int i = 1; i <= 10; i++) {
            manager.addParkingSpot(new ParkingSpot("A1-" + String.format("%03d", i),
                    ParkingSpotType.REGULAR, 1, i));
        }
        manager.parkingStatus();

        List<Thread> parkingThreads = new ArrayList<>();
        List<Ticket> successfulTickets = Collections.synchronizedList(new ArrayList<>());
        List<String> failedAttempts = Collections.synchronizedList(new ArrayList<>());

        for(int i=1; i<=20; i++){
            int finalI = i;
            Thread thread = new Thread(() ->{
                try{
                    Vehicle vehicle = new Vehicle("CAR" + finalI, VehicleType.CAR, "ABC");
                    Thread.sleep((long)(Math.random()*100));
                    Ticket ticket = manager.parkVehicle(vehicle);
                    successfulTickets.add(ticket);
                } catch (Exception e) {
                    failedAttempts.add("CAR" + finalI + ": " + e.getMessage());
                }
            });
            parkingThreads.add(thread);
        }

        for (Thread thread : parkingThreads) {
            thread.start();
        }

        for(Thread thread : parkingThreads){
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n STRESS TEST RESULTS:");
//        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Successful parkings: " + successfulTickets.size());

        System.out.println("\n Successful attempts:");
        for (Ticket ticket : successfulTickets) {
            System.out.println("  " + ticket.getVehicleId());
        }

        System.out.println("Failed attempts: " + failedAttempts.size());
        System.out.println("\n Failed attempts:");
        for (String failure : failedAttempts) {
            System.out.println("  " + failure);
        }

        manager.parkingStatus();


//
//        Vehicle car1 = new Vehicle("C01", VehicleType.CAR, "N001");
//        Vehicle car2 = new Vehicle("C02", VehicleType.CAR, "N002");
//        Vehicle car3 = new Vehicle("C03", VehicleType.CAR, "N003");
//
//        Thread thread1 = new Thread( ()->{
//            try{
//                System.out.println("Starting to park car1");
//                manager.parkVehicle(car1);
//                System.out.println("Parked Car1 successfully");
//            } catch (Exception e){
//                System.out.println("Failed to park car1 using thread 1: "+  e.getMessage());
//            }
//        });
//
//        Thread thread2 = new Thread( ()->{
//            try{
//                System.out.println("Starting to park car2");
//                manager.parkVehicle(car2);
//                System.out.println("Parked Car2 successfully");
//            } catch (Exception e){
//                System.out.println("Failed to park car2 using thread 2: "+  e.getMessage());
//            }
//        });
//
//        Thread thread3 = new Thread( ()->{
//            try{
//                System.out.println("Starting to park car3");
//                manager.parkVehicle(car3);
//                System.out.println("Parked Car3 successfully");
//            } catch (Exception e){
//                System.out.println("Failed to park car3 using thread 3: "+  e.getMessage());
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//
//        try {
//            thread1.sleep(1000);
//            thread2.sleep(1000);
//            thread3.sleep(1000);
//            thread1.join();
//            thread2.join();
//            thread3.join();
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//
//        manager.parkingStatus();


//        manager.addParkingSpot(new ParkingSpot("1", ParkingSpotType.COMPACT, 1, 1));
//        manager.addParkingSpot(new ParkingSpot("2", ParkingSpotType.REGULAR, 1, 2));
//        manager.addParkingSpot(new ParkingSpot("3", ParkingSpotType.LARGE, 1, 3));
//        manager.addParkingSpot(new ParkingSpot("4", ParkingSpotType.REGULAR, 2, 1));
//
//        List<Vehicle> vehicleList = new ArrayList<>();
//        manager.parkingStatus();
//        try{
//
//            for(ParkingSpot parkingSpot: manager.allParkingSpots){
//                System.out.println(parkingSpot.getAvailable());
//            }
//            for(int i=0; i<5; i++){
//                vehicleList.add(new Vehicle(  "1" + String.valueOf(i), VehicleType.CAR, "A00" + String.valueOf(i)));
//            }
//
//            for(int i=0; i<5; i++){
//                manager.parkVehicle(vehicleList.get(i));
//                manager.parkingStatus();
//            }
//        } catch (Exception e){
//            System.out.println("Error: " + e.getMessage());
//        }
    }
}