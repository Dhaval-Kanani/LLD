package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.Card;
import org.modules.SystemDesign.ATMSystem.TransactionType;

public abstract class ATMState {
    public void insertCard(ATM atm, Card card){
        System.out.println("OOPS. Something went wrong! ");
    }

    public void authenticatePin(ATM atm, Card card, String enteredPin){
        System.out.println("OOPS. Something went wrong! ");
    }

    public void selectOperation(ATM atm, TransactionType transactionType){
        System.out.println("OOPS. Something went wrong! ");
    }

    public void withdrawBalance(ATM atm, Card card, int withdrawAmt){
        System.out.println("OOPS. Something went wrong! ");
    }

    public void checkBalance(ATM atm, Card card){
        System.out.println("OOPS. Something went wrong! ");
    }

    public void exitATM(ATM atm){
        System.out.println("OOPS. Something went wrong! ");
    }
}
