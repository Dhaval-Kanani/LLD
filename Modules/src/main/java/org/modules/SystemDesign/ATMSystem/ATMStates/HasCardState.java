package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.Card;

import java.io.PrintStream;

public class HasCardState extends ATMState {
    public HasCardState(){
        System.out.println("Enter PIN");
    }

    @Override
    public void authenticatePin(ATM atm, Card card, String enteredPin){
        System.out.println("PIN Entered " + enteredPin);
        boolean isCorrectPin = card.checkEnteredPin(enteredPin);

        if(!isCorrectPin){
            System.out.println("Invalid PIN!!");
            exitATM(atm);
        } else{
            atm.setAtmState(new SelectOpState());
        }
    }

    @Override
    public void exitATM(ATM atm){
        System.out.println("Please Collect your card");
        System.out.println("Exited");
        atm.setAtmState(new IdleState());
    }
}
