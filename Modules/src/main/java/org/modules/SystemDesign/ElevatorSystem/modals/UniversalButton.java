package org.modules.SystemDesign.ElevatorSystem.modals;

import org.modules.SystemDesign.ElevatorSystem.enums.ButtonType;
import org.modules.SystemDesign.ElevatorSystem.interfaces.Button;


public class UniversalButton implements Button {
    private ButtonType buttonType;
    private Object target;
    private Boolean isPressed;

    public UniversalButton(ButtonType buttonType, Object target) {
        this.buttonType = buttonType;
        this.target = target;
        this.isPressed = false;
    }

    @Override
    public Boolean isPressed() {
        return isPressed;
    }

    @Override
    public Boolean pressButton() {
        isPressed = !isPressed;
        return isPressed;
    }

    @Override
    public ButtonType getButtonType(){
        return buttonType;
    }
}
