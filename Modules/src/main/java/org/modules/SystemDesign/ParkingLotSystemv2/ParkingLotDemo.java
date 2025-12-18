package org.modules.SystemDesign.ParkingLotSystemv2;

import org.modules.SystemDesign.ParkingLotSystemv2.Strategy.HourlyRateFeeCalculator;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.Floors;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.ParkingSize;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.VehicleType;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLotDemo {
    private ParkingLotController parkingLotController;

    public static void main(String[] args) {
        ParkingLotDemo parkingLotDemo = new ParkingLotDemo();
        parkingLotDemo.initialize();
        parkingLotDemo.demo();
    }

    public void demo(){
        Map<String, Ticket> ticketMap = new ConcurrentHashMap<>();
        AtomicInteger successfullcount = new AtomicInteger();
        AtomicInteger unsuccessfullcount = new AtomicInteger();
        List<CompletableFuture> futures = new ArrayList<>();

        for(int i=1; i<=8; i++){
            for(VehicleType vehicleType: VehicleType.values()){
                futures.add(CompletableFuture.runAsync(()->{
                    Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(), UUID.randomUUID().toString(), vehicleType);
                    Ticket ticket = parkingLotController.parkVehicle(vehicle);
                    if(ticket == null) {
                        unsuccessfullcount.getAndIncrement();
                        return;
                    } else successfullcount.getAndIncrement();
                    ticketMap.put(vehicle.getVehicleId(), ticket);
                }));
            }
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        for(String key: ticketMap.keySet()){
            Ticket ticket = ticketMap.get(key);
            parkingLotController.unparkVehicle(ticket, 100.0);
        }
        System.out.println("Success: " + successfullcount + " Not success: " + unsuccessfullcount);
    }

    public void initialize(){
        HourlyRateFeeCalculator hourlyRateFeeCalculator = new HourlyRateFeeCalculator();
        parkingLotController = ParkingLotController.getInstance(hourlyRateFeeCalculator);

        Map<String, ParkingSpot> parkingSpotMap = new HashMap<>();
        for(int i=1; i<=3; i++){
            ParkingSpot parkingSpot1 = new ParkingSpot(UUID.randomUUID().toString(), ParkingSize.COMPACT);
            ParkingSpot parkingSpot2 = new ParkingSpot(UUID.randomUUID().toString(), ParkingSize.MEDIUM);
            ParkingSpot parkingSpot3 = new ParkingSpot(UUID.randomUUID().toString(), ParkingSize.LARGE);
            parkingSpotMap.put(parkingSpot1.getParkingSpotId(), parkingSpot1);
            parkingSpotMap.put(parkingSpot2.getParkingSpotId(), parkingSpot2);
            parkingSpotMap.put(parkingSpot3.getParkingSpotId(), parkingSpot3);
        }
        for(Floors floor: Floors.values()){
            ParkingFloor parkingFloor = new ParkingFloor(floor);
            parkingFloor.setParkingSpots(parkingSpotMap);
            parkingLotController.addFloorMap(parkingFloor);
        }
    }
}
