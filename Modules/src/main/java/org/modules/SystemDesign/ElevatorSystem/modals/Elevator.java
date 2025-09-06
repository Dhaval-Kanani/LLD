package org.modules.SystemDesign.ElevatorSystem.modals;

import org.modules.SystemDesign.ElevatorSystem.enums.ButtonType;
import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.ElevatorNumber;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;
import org.modules.SystemDesign.ElevatorSystem.factory.ButtonFactory;
import org.modules.SystemDesign.ElevatorSystem.interfaces.Button;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class Elevator {
    private ElevatorNumber elevatorNumber;
    private Map<FloorNumber, Button> floorButtons;
    private Map<ButtonType, Button> doorButtons;
    private FloorNumber currentFloor;
    private Boolean isMoving;
    private Direction currentDirection;

    private Queue<Request> requests;

    public Elevator(ElevatorNumber elevatorNumber, FloorNumber currentFloor) {
        this.elevatorNumber = elevatorNumber;
        this.currentFloor = currentFloor;
        this.currentDirection = Direction.IDLE;
        initialize();
    }

    private void initialize(){
        this.floorButtons = new HashMap<>();
        this.doorButtons = new HashMap<>();
        this.isMoving = false;
        this.requests = new LinkedList<>();

        for(int i=0; i<FloorNumber.values().length; i++){
            FloorNumber floorNumber = FloorNumber.values()[i];
            floorButtons.put(floorNumber, ButtonFactory.createButton(floorNumber));
        }

        doorButtons.put(ButtonType.DOOR_OPEN_BUTTON, ButtonFactory.createButton(ButtonType.DOOR_OPEN_BUTTON));
        doorButtons.put(ButtonType.DOOR_CLOSE_BUTTON, ButtonFactory.createButton(ButtonType.DOOR_CLOSE_BUTTON));
    }

    public void pressElevatorButton(FloorNumber floorNumber){
        Button button = floorButtons.get(floorNumber);
        if(button!=null){
            if(button.pressButton()) {
                System.out.println("Elevator " + elevatorNumber + " - Floor " + floorNumber + " pressed");
                addRequest(new Request(floorNumber, Direction.IDLE));
            } else {
                System.out.println("Request of elevator to move to " + floorNumber + " cancelled");
            }
        }
    }

    public void addRequest(Request request){
        requests.offer(request);
        System.out.println("Request added to Elevator " + elevatorNumber + ": Floor " + request.getFloorNumber());
        if(!isMoving){
            processRequest();
        }
    }

    private void processRequest(){
        if(requests.isEmpty()){
            currentDirection = Direction.IDLE;
            System.out.println("All request processed");
            return;
        }
        Request request = requests.poll();
        moveElevator(request.getFloorNumber());
    }

    private void moveElevator(FloorNumber destinationFloor){
        if(destinationFloor == currentFloor){
            System.out.println("Elevator already at destination floor");
            return;
        }

        if(destinationFloor.ordinal() > currentFloor.ordinal()){
            currentDirection = Direction.UP;
            System.out.println("Moving up from " + currentFloor + " to " + destinationFloor);
        } else{
            currentDirection = Direction.DOWN;
            System.out.println("Moving down from " + currentFloor + " to " + destinationFloor);
        }

        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep((Math.abs(destinationFloor.ordinal() - currentFloor.ordinal())*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenAccept(r -> {
            currentFloor = destinationFloor;
            currentDirection = Direction.IDLE;
            System.out.println("Elevator reached the destination Floor");
        });
//        try{
//            Thread.sleep((Math.abs(destinationFloor.ordinal() - currentFloor.ordinal())*1000));
//        } catch (InterruptedException e){
//            Thread.currentThread().interrupt();
//        }
//
//        currentFloor = destinationFloor;
//        currentDirection = Direction.IDLE;
//        System.out.println("Elevator reached the destination Floor");
    }

    public ElevatorNumber getElevatorNumber() {
        return elevatorNumber;
    }

    public int getDistanceFromFloor(FloorNumber floorNumber){
        return Math.abs(floorNumber.ordinal() - currentFloor.ordinal());
    }

    public Boolean getMoving() {
        return isMoving;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public FloorNumber getCurrentFloor() {
        return currentFloor;
    }

    public Queue<Request> getRequests() {
        return requests;
    }
}

