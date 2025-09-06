package org.modules.SystemDesign.ElevatorSystem.factory;

import org.modules.SystemDesign.ElevatorSystem.enums.ButtonType;
import org.modules.SystemDesign.ElevatorSystem.enums.Direction;
import org.modules.SystemDesign.ElevatorSystem.enums.FloorNumber;
import org.modules.SystemDesign.ElevatorSystem.modals.UniversalButton;



public class ButtonFactory {
    public static UniversalButton createButton(Object targetObject) {

        if(targetObject instanceof FloorNumber floorNumber){
            return new UniversalButton(ButtonType.ELEVATOR_BUTTON, floorNumber);
        } else if(targetObject instanceof Direction direction){
            return new UniversalButton(direction == Direction.UP ? ButtonType.CALL_UP_BUTTON : ButtonType.CALL_DOWN_BUTTON, direction);
        } else if(targetObject instanceof ButtonType buttonType){
            return new UniversalButton(buttonType, buttonType);
        } else {
            System.out.println("Invalid type of target object");
            return null;
        }

    }
}
