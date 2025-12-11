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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Elevator {
    private ElevatorNumber elevatorNumber;
    private Map<FloorNumber, Button> floorButtons;
    private Map<ButtonType, Button> doorButtons;
    private FloorNumber currentFloor;
    private Boolean isMoving;
    private Direction currentDirection;

    private BlockingQueue<Request> requests;
    private AtomicBoolean running;

    public Elevator(ElevatorNumber elevatorNumber, FloorNumber currentFloor) {
        this.elevatorNumber = elevatorNumber;
        this.currentFloor = currentFloor;
        this.currentDirection = Direction.IDLE;
        this.running = new AtomicBoolean(true);
        initialize();
    }

    private void initialize(){
        this.floorButtons = new HashMap<>();
        this.doorButtons = new HashMap<>();
        this.isMoving = false;
        this.requests = new LinkedBlockingQueue<>();

        for(int i=0; i<FloorNumber.values().length; i++){
            FloorNumber floorNumber = FloorNumber.values()[i];
            floorButtons.put(floorNumber, ButtonFactory.createButton(floorNumber));
        }

        doorButtons.put(ButtonType.DOOR_OPEN_BUTTON, ButtonFactory.createButton(ButtonType.DOOR_OPEN_BUTTON));
        doorButtons.put(ButtonType.DOOR_CLOSE_BUTTON, ButtonFactory.createButton(ButtonType.DOOR_CLOSE_BUTTON));
        processRequest();
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
        try{
            requests.put(request);
        } catch (InterruptedException e){
            System.out.println("interrupted");
        }
    }

    private void processRequest(){

        CompletableFuture.runAsync(() ->{
            while(running.get()){
                try {
                    Request request = requests.take();
                    System.out.println("Consuming: consuming request: " + currentFloor + " to " + request.getFloorNumber());
                    moveElevator(request.getFloorNumber());
                    System.out.println("Consumed: consumed request: " + request.getFloorNumber() + " " + request.getDirection());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Elevator: " + elevatorNumber + " stopped");
        });

//        Thread consumerThread = new Thread(()->{
//            try{
//                while(true){
//                    Request request = requests.take();
//                    Thread.sleep(1000);
//                    System.out.println("Consuming: consuming request: " + currentFloor + " to " + request.getFloorNumber());
//                    moveElevator(request.getFloorNumber());
//                    System.out.println("Consumed: consumed request: " + request.getFloorNumber() + " " + request.getDirection());
//                }
//            } catch (InterruptedException e) {
//                System.out.println("consumer thread interrupted");
//            }
//        });
//        consumerThread.start();
    }

    public void suspend(){
        this.running.set(false);
    }

    private void moveElevator(FloorNumber destinationFloor) {
        if(destinationFloor == currentFloor){
            System.out.println("Elevator already at destination floor");
            return;
        }

        if(destinationFloor.ordinal() > currentFloor.ordinal()){
            currentDirection = Direction.UP;
            System.out.println(elevatorNumber + " Moving up from " + currentFloor + " to " + destinationFloor);
        } else{
            currentDirection = Direction.DOWN;
            System.out.println(elevatorNumber + " Moving down from " + currentFloor + " to " + destinationFloor);
        }
        CompletableFuture.runAsync(() -> {
            try{
                Thread.sleep((Math.abs(destinationFloor.ordinal() - currentFloor.ordinal())*1000));
            } catch (InterruptedException e){
                System.out.println("moveElevator fun interrupted");
            }
        }).thenAccept(r -> {
            currentFloor = destinationFloor;
            currentDirection = Direction.IDLE;
            System.out.println("Elevator reached the destination Floor " + currentFloor);
        });
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

