package org.modules.SystemDesign.ATMSystem.WithdrawalSys;

import org.modules.SystemDesign.ATMSystem.ATM;

public class TwoThousandProcessor extends WithdrawalProcessor{

    @Override
    public void withdraw(ATM atm, int amount) {
        int noOfNotes = atm.getNoOfTwoThousandNotes();

        int requiredNoOfNotes = amount/2000;
        int reminder = amount%2000;

        if(requiredNoOfNotes<=noOfNotes){
            atm.setNoOfTwoThousandNotes(noOfNotes - requiredNoOfNotes);
            atm.setTotalBalance(atm.getTotalBalance() - requiredNoOfNotes*2000);
        } else{
            atm.setNoOfTwoThousandNotes(0);
            atm.setTotalBalance(atm.getTotalBalance() - noOfNotes*2000);
            reminder += (requiredNoOfNotes - noOfNotes)*2000;
        }

        if(reminder!=0){

            withdrawAmount(atm, reminder);
        }
    }
}
