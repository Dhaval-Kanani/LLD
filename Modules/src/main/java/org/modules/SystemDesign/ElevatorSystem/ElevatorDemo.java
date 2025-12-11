package org.modules.SystemDesign.ElevatorSystem;

import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.ElevatorNumber;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;
import org.modules.SystemDesign.ElevatorSystem.modals.Elevator;
import org.modules.SystemDesign.ElevatorSystem.modals.Floor;


public class ElevatorDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("----Elevator System----");
        ElevatorManager elevatorManager = new ElevatorManager();

        for(int i=0; i< ElevatorNumber.values().length; i++){
            Elevator elevator = new Elevator(ElevatorNumber.values()[i], FloorNumber.values()[i]);
            elevatorManager.addElevator(elevator);
        }

        for(int i=0; i< FloorNumber.values().length; i++){
            elevatorManager.addFloor(new Floor(FloorNumber.values()[i]));
        }

        elevatorManager.requestElevator(FloorNumber.FLOOR5, Direction.UP);
        Thread.sleep(2000);

        elevatorManager.requestFloor(ElevatorNumber.ELEVATOR3, FloorNumber.FLOOR8);
        Thread.sleep(3000);

        elevatorManager.requestElevator(FloorNumber.FLOOR5, Direction.DOWN);
        elevatorManager.requestElevator(FloorNumber.FLOOR7, Direction.UP);
        elevatorManager.requestFloor(ElevatorNumber.ELEVATOR1, FloorNumber.FLOOR1);
        Thread.sleep(2000);

        elevatorManager.showStatus();

        elevatorManager.requestElevator(FloorNumber.FLOOR8, Direction.UP);
        elevatorManager.requestElevator(FloorNumber.FLOOR1, Direction.DOWN);
        elevatorManager.requestFloor(ElevatorNumber.ELEVATOR3, FloorNumber.FLOOR4);

        elevatorManager.showStatus();


        elevatorManager.requestElevator(FloorNumber.FLOOR5, Direction.UP);
        elevatorManager.requestElevator(FloorNumber.FLOOR3, Direction.DOWN);

        elevatorManager.requestFloor(ElevatorNumber.ELEVATOR2, FloorNumber.FLOOR8);

        Thread.sleep(2000);

        elevatorManager.showStatus();

        System.out.println("Demo completed!!");
//        elevatorManager.suspendAllElevators();

    }

}
