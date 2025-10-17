package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.BankAccount;
import org.modules.SystemDesign.ATMSystem.Card;

public class CheckBalanceState extends ATMState{
    public CheckBalanceState(){
        System.out.println("Checking Balance....");
    }

    @Override
    public void checkBalance(ATM atm, Card card) {
        BankAccount bankAccount = card.getBankAccount();
        System.out.println("BALANCE: " + bankAccount.getAmount());
        exitATM(atm);
    }

    @Override
    public void exitATM(ATM atm){
        System.out.println("Please Collect your card");
        System.out.println("Exited");
        atm.setAtmState(new IdleState());
    }
}
