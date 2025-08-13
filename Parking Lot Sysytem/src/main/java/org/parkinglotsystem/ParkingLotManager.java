package org.parkinglotsystem;

import org.parkinglotsystem.entity.FeeCalculator;
import org.parkinglotsystem.entity.ParkingSpot;
import org.parkinglotsystem.entity.Ticket;
import org.parkinglotsystem.entity.Vehicle;
import org.parkinglotsystem.enums.PaymentType;
import org.parkinglotsystem.enums.VehicleType;
import org.parkinglotsystem.exceptions.ParkingLotException;
import org.parkinglotsystem.exceptions.PaymentException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingLotManager {
    public List<ParkingSpot> allParkingSpots;
    private final ConcurrentHashMap<String, Ticket> activeTicket;
    private final ConcurrentHashMap<String, Vehicle> parkedVehicles;
    private final AtomicInteger ticketCounter;

    public ParkingLotManager() {
        this.allParkingSpots = new ArrayList<>();
        this.activeTicket = new ConcurrentHashMap<>();
        this.parkedVehicles = new ConcurrentHashMap<>();
        this.ticketCounter = new AtomicInteger(1);
    }
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void addParkingSpot(ParkingSpot parkingSpot){
        lock.writeLock().lock();
        try {
            allParkingSpots.add(parkingSpot);
        } finally {
            lock.writeLock().unlock();
        }

    }

    public Ticket parkVehicle(Vehicle vehicle) throws ParkingLotException {
        try {
            if (parkedVehicles.containsKey(vehicle.getVehicleId())) {
                throw new ParkingLotException("Vehicle is already parked with: " + vehicle.getVehicleId());
            }

            ParkingSpot parkingSpot = findAvailableSpot(vehicle.getVehicleType());
            if(parkingSpot == null) {
                throw new ParkingLotException("No Parking Slot is available for parking");
            }

            Boolean parkingSuccess = parkingSpot.parkVehicle(vehicle);
            if(!parkingSuccess){
                throw new ParkingLotException("Failed to park vehicle in the spot: " + parkingSpot.getParkingSpotId());
            }

            vehicle.parkVehicle(parkingSpot);
            parkedVehicles.put(vehicle.getVehicleId(), vehicle);

            int counter = ticketCounter.getAndIncrement();
            Ticket ticket = new Ticket("A001" + counter, vehicle.getVehicleId(), parkingSpot.getParkingSpotId());
            activeTicket.put(ticket.getTicketId(), ticket);
            System.out.println("Successfully vehicle parked with id: " + vehicle.getVehicleId() + " at parking spot: " + parkingSpot.getParkingSpotId());
            return ticket;
        } catch (Exception e){
            throw new ParkingLotException("Failed to park the vehicle: " + e);
        }
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType){
        for(ParkingSpot parkingSpot: allParkingSpots){
            if(parkingSpot.getAvailable() && parkingSpot.canAccommodate(vehicleType)){
                return parkingSpot;
            }
        }
        return null;
    }

    public double unParkVehicle(String tickedId, PaymentType paymentType, double amountPaid) throws ParkingLotException, PaymentException {
        try{
            if(!activeTicket.containsKey(tickedId)){
                throw new ParkingLotException("Invalid Ticket id: "+ tickedId);
            }
            Ticket ticket = activeTicket.get(tickedId);
            Vehicle vehicle = parkedVehicles.get(ticket.getVehicleId());
            if(vehicle==null){
                throw new ParkingLotException("Vehicle not found with ticket id: " + tickedId);
            }

            if(!parkedVehicles.containsKey(vehicle.getVehicleId())) {
                throw new ParkingLotException("Vehicle is not parked with id: " + vehicle.getVehicleId());
            }

            double totalHrs = ticket.calculateHours();
            double totalFare = FeeCalculator.calculateFee(totalHrs, vehicle.getVehicleType());

            ticket.exitVehicle(totalFare);

            Boolean paymentDone = ticket.makePayment(paymentType, amountPaid);
            if(!paymentDone){
                throw new ParkingLotException("Payment failed");
            }

            ParkingSpot parkingSpot = vehicle.getAssignedSpot();


            parkingSpot.unParkVehicle();
            vehicle.unParkVehicle();

            parkedVehicles.remove(vehicle.getVehicleId());
            activeTicket.remove(tickedId);

            double change = amountPaid - totalFare;

            System.out.println("Successfully unparked the vehicle. Total duration: " + totalFare +
                    " vehicle: "+ vehicle.getVehicleType() + " total fare: "+ totalFare + " change: " + change);
            return change;
        } catch (Exception e){
            throw new ParkingLotException("Failed to unpark the vehicle: " + e);
        }
    }

    public void parkingStatus(){
        System.out.println("Total Parked Vehicle: " + activeTicket.size());
        System.out.println("Remaining Spot available: " + (allParkingSpots.size() - activeTicket.size()));
        System.out.println("Active Tickets: " + activeTicket.size());
//        System.out.println(activeTicket.get("A001").getVehicleId());
    }
}
