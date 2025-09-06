package org.modules.SystemDesign.ElevatorSystem.interfaces;

import org.modules.SystemDesign.ElevatorSystem.enums.ButtonType;

public interface Button {
    ButtonType getButtonType();
    Boolean isPressed();
    Boolean pressButton();
}
