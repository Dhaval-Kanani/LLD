package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.TransactionType;

public class SelectOpState extends ATMState{
    public SelectOpState(){
        System.out.println("List of operations: ");
        TransactionType.getAllOps();
    }

    @Override
    public void selectOperation(ATM atm, TransactionType transactionType) {
        switch (transactionType){
            case CHECK_BALANCE -> atm.setAtmState(new CheckBalanceState());
            case CASH_WITHDRAWAL -> atm.setAtmState(new WithdrawalState());
            default -> {
                System.out.println("Invalid operation selected");
                exitATM(atm);
            }
        }
    }

    @Override
    public void exitATM(ATM atm){
        System.out.println("Please Collect your card");
        System.out.println("Exited");
        atm.setAtmState(new IdleState());
    }
}
