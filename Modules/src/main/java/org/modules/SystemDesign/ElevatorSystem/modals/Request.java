package org.modules.SystemDesign.ElevatorSystem.modals;


import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;

public class Request {
    private FloorNumber floorNumber;
    private Direction direction;

    public Request(FloorNumber floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
