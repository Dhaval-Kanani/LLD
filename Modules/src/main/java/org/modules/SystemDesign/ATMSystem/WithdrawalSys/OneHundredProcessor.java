package org.modules.SystemDesign.ATMSystem.WithdrawalSys;

import org.modules.SystemDesign.ATMSystem.ATM;

public class OneHundredProcessor extends WithdrawalProcessor{
    private static final int currencyAmt = 100;
    @Override
    public void withdraw(ATM atm, int amount) {
        int noOfNotes = atm.getNoOfHundredNotes();

        int requiredNoOfNotes = amount/currencyAmt;
        int reminder = amount%currencyAmt;

        if(requiredNoOfNotes<=noOfNotes){
            atm.setNoOfHundredNotes(noOfNotes - requiredNoOfNotes);
            atm.setTotalBalance(atm.getTotalBalance() - requiredNoOfNotes*currencyAmt);
        } else{
            atm.setNoOfHundredNotes(0);
            atm.setTotalBalance(atm.getTotalBalance() - noOfNotes*currencyAmt);
            reminder += (requiredNoOfNotes - noOfNotes)*currencyAmt;
        }

        if(reminder!=0){
            withdrawAmount(atm, reminder);
        }
    }
}
