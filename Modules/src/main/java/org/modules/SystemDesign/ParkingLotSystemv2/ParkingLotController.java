package org.modules.SystemDesign.ParkingLotSystemv2;

import org.modules.SystemDesign.ParkingLotSystemv2.Strategy.FeeStrategy;
import org.modules.SystemDesign.ParkingLotSystemv2.enums.Floors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLotController {
    private static ParkingLotController parkingLotController;
    private static final Object lock = new Object();
    private Map<Floors, ParkingFloor> floorMap;
    private Map<String, Ticket> activeTickets;
    private AtomicInteger ticketId;
    private FeeStrategy feeStrategy;

    public static ParkingLotController getInstance(FeeStrategy feeStrategy){
        if(parkingLotController == null){
            synchronized (lock){
                if(parkingLotController==null){
                    parkingLotController = new ParkingLotController(feeStrategy);
                }
            }
        }
        return parkingLotController;
    }


    private ParkingLotController(FeeStrategy feeStrategy) {
        this.floorMap = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.ticketId = new AtomicInteger(1);
        this.feeStrategy = feeStrategy;
    }

    public Map<Floors, ParkingFloor> getFloorMap() {
        return floorMap;
    }

    public void setFloorMap(Map<Floors, ParkingFloor> floorMap) {
        this.floorMap = floorMap;
    }

    public void addFloorMap(ParkingFloor floor) {
        this.floorMap.put(floor.getFloor(), floor);
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot = findAvailableSpot(vehicle);
        if(parkingSpot == null) {
            System.out.println("Failed to park vehicle: " + vehicle.getVehicleType());
            return null;
        }
        parkingSpot.setAvailable(false);
        vehicle.setParked(true);

        Ticket ticket = new Ticket(ticketId.toString(), vehicle, parkingSpot);
        ticketId.incrementAndGet();
        activeTickets.put(ticket.getTicketId(), ticket);
        System.out.println("Vehicle parked successfully: " + vehicle.getVehicleType());

        return ticket;
    }

    private ParkingSpot findAvailableSpot(Vehicle vehicle){
        ParkingSpot parkingSpot = null;
        for(Floors floor: Floors.values()){
            ParkingFloor parkingFloor = floorMap.get(floor);
            parkingSpot = parkingFloor.findAvailableParkingSpot(vehicle.getVehicleType());
            if(parkingSpot!=null) return parkingSpot;
        }

        System.out.println("No Parking spot found for vehicle type: " + vehicle.getVehicleType());
        return parkingSpot;
    }

    public Boolean unparkVehicle(Ticket ticket, Double amountPaid){
        if(!activeTickets.containsKey(ticket.getTicketId())){
            System.out.println("Invalid ticket! No parking vehicle exist for ticketId: " + ticket.getTicketId());
            return false;
        }


        Vehicle vehicle= ticket.getVehicle();
        ParkingSpot parkingSpot = ticket.getParkingSpot();

        vehicle.setParked(false);
        parkingSpot.setAvailable(true);

        ticket.setParkingEndTime(System.currentTimeMillis() + 1000*60*60*3);

        Double charge = feeStrategy.calculateFee(ticket);
        if(amountPaid < charge){
            System.out.println("Please pay amount : " + charge);
            return false;
        }
        ticket.setPaid(true);
        activeTickets.remove(ticket.getTicketId());
        System.out.println("Successfully unparked the vehicle. Return the amount to user: " + (amountPaid - charge));
        return true;
    }
}
