package org.modules.SystemDesign.ElevatorSystem.modals;

import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;
import org.modules.SystemDesign.ElevatorSystem.factory.ButtonFactory;
import org.modules.SystemDesign.ElevatorSystem.interfaces.Button;


import java.util.HashMap;
import java.util.Map;

public class Floor {
    private FloorNumber floorNumber;
    private Map<Direction, Button> reqButtons;

    public Floor(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        initializeButtons();
    }

    private void initializeButtons(){
        this.reqButtons = new HashMap<>();

        if(this.floorNumber.ordinal()<10){
            reqButtons.put(Direction.UP, ButtonFactory.createButton(Direction.UP));
        }
        if(this.floorNumber.ordinal()>1){
            reqButtons.put(Direction.DOWN, ButtonFactory.createButton(Direction.DOWN));
        }
    }

    public void requestElevator(Direction direction){
        Button button = reqButtons.get(direction);
        if(button!=null){
            System.out.println("Floor " + floorNumber + " " + direction + " button pressed");
            button.pressButton();
        } else System.out.println("Invalid Direction");
    }

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }
}
