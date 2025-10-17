package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.Card;

public class IdleState extends ATMState{
    public IdleState(){
        System.out.println("-----------ATM------------");
        System.out.println("Enter the card");
    }

    @Override
    public void insertCard(ATM atm, Card card){
        System.out.println("Card Entered");
        atm.setAtmState(new HasCardState());
    }
}
