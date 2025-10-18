package org.modules.SystemDesign.ATMSystem.ATMStates;

import org.modules.SystemDesign.ATMSystem.ATM;
import org.modules.SystemDesign.ATMSystem.Card;
import org.modules.SystemDesign.ATMSystem.WithdrawalSys.WithdrawalManager;
import org.modules.SystemDesign.ATMSystem.WithdrawalSys.WithdrawalProcessor;

public class WithdrawalState extends ATMState{
    public WithdrawalState(){
        System.out.println("Enter the withdrawal amount");
    }

    @Override
    public void withdrawBalance(ATM atm, Card card, int withdrawAmt){
        System.out.println("Withdraw Request Amount - " + withdrawAmt);

        int atmBal = atm.getTotalBalance();
        int accountBal = card.getBankAccount().getAmount();

        if(withdrawAmt>atmBal){
            System.out.println("Enough amount is not present in atm to withdraw");
        } else if (withdrawAmt>accountBal){
            System.out.println("Enough amount is not present in your account to withdraw");
            System.out.println("Account Bal - " + accountBal);
        } else{

            WithdrawalProcessor withdrawalProcessor = WithdrawalManager.getWithdrawalProcessor();
            withdrawalProcessor.withdraw(atm, withdrawAmt);

            card.getBankAccount().setAmount(accountBal - withdrawAmt);
            System.out.println("Amount withdrawn");
            atm.status();
        }

        exitATM(atm);
    }

    @Override
    public void exitATM(ATM atm){
        System.out.println("Please Collect your card");
        System.out.println("Exited");
        atm.setAtmState(new IdleState());
    }
}
