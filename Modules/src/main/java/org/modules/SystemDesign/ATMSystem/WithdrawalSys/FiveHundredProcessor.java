package org.modules.SystemDesign.ATMSystem.WithdrawalSys;

import org.modules.SystemDesign.ATMSystem.ATM;

public class FiveHundredProcessor extends WithdrawalProcessor{
    @Override
    public void withdraw(ATM atm, int amount) {
        int noOfNotes = atm.getNoOfFiveHundredNotes();

        int requiredNoOfNotes = amount/500;
        int reminder = amount%500;

        if(requiredNoOfNotes<=noOfNotes){
            atm.setNoOfFiveHundredNotes(noOfNotes - requiredNoOfNotes);
            atm.setTotalBalance(atm.getTotalBalance() - requiredNoOfNotes*500);
        } else{
            atm.setNoOfFiveHundredNotes(0);
            atm.setTotalBalance(atm.getTotalBalance() - noOfNotes*500);
            reminder += (requiredNoOfNotes - noOfNotes)*500;
        }

        if(reminder!=0){
            withdrawAmount(atm, reminder);
        }
    }
}
