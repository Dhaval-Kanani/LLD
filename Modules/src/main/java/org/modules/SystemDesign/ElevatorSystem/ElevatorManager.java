package org.modules.SystemDesign.ElevatorSystem;

import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.ElevatorNumber;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;
import org.modules.SystemDesign.ElevatorSystem.modals.Elevator;
import org.modules.SystemDesign.ElevatorSystem.modals.Floor;
import org.modules.SystemDesign.ElevatorSystem.modals.Request;


import java.util.HashMap;
import java.util.Map;

public class ElevatorManager {
    private Map<ElevatorNumber, Elevator> elevatorMap;
    private Map<FloorNumber, Floor> floorMap;

    public ElevatorManager() {
        this.elevatorMap = new HashMap<>();
        this.floorMap = new HashMap<>();
    }

    public void addElevator(Elevator elevator){
        elevatorMap.putIfAbsent(elevator.getElevatorNumber(), elevator);
    }

    public void addFloor(Floor floor){
        floorMap.putIfAbsent(floor.getFloorNumber(), floor);
    }

    public void requestElevator(FloorNumber floorNumber, Direction direction){
        Floor floor = floorMap.get(floorNumber);
        if(floor == null){
            System.out.println("Floor not exist");
            return;
        }

        floor.requestElevator(direction);

        Elevator elevator = findOptimalElevator(floorNumber);
        Request request = new Request(floorNumber, direction);
        elevator.addRequest(request);
        System.out.println("Request from Floor: Requested from "  + floorNumber + " for "  +direction + " direction.");
    }

    private Elevator findOptimalElevator(FloorNumber floorNumber){
        Elevator bestElevator = null;
        int nearestDist = Integer.MAX_VALUE;

        for(Elevator elevator: elevatorMap.values()){
            if(!elevator.getMoving()) return elevator;
            int dist = elevator.getDistanceFromFloor(floorNumber);
            if(nearestDist > dist) {
                nearestDist = dist;
                bestElevator = elevator;
            }
        }
        return bestElevator==null ? elevatorMap.get(ElevatorNumber.values()[0]) : bestElevator;
    }

    public void requestFloor(ElevatorNumber elevatorNumber, FloorNumber destinationFloor){
//        System.out.println("Requesting elevator to move to " + destinationFloor);

        Elevator elevator = elevatorMap.get(elevatorNumber);
        if(elevator == null){
            System.out.println("Elevator not found");
            return;
        }
        elevator.pressElevatorButton(destinationFloor);
        System.out.println("Request from Elevator: Request added to Elevator " + elevatorNumber + ": Floor " + destinationFloor);
    }

    public void suspendAllElevators(){
        for(ElevatorNumber elevatorNumber : elevatorMap.keySet()){
            Elevator elevator = elevatorMap.get(elevatorNumber);
            elevator.suspend();
        }
    }

    public void showStatus(){
        System.out.println("Elevator status -------------");

        for(Elevator elevator : elevatorMap.values()){
            System.out.println("Elevator - " + elevator.getElevatorNumber()
             + " | current Floor - " + elevator.getCurrentFloor()
             + " | current direction - " + elevator.getCurrentDirection()
             + " | moving: " + elevator.getMoving()
             + " | pending requests - " + elevator.getRequests().size());
        }

        System.out.println("---------------------------");
    }
}
