package org.modules.SystemDesign.ATMSystem.WithdrawalSys;

import org.modules.SystemDesign.ATMSystem.ATM;

public abstract class WithdrawalProcessor {
    private WithdrawalProcessor nextWithdrawalProcessor;

    public void setNextWithdrawalProcessor(WithdrawalProcessor withdrawalProcessor){
        this.nextWithdrawalProcessor = withdrawalProcessor;
    }

    public void withdrawAmount(ATM atm, int amount){
        if(this.nextWithdrawalProcessor!=null){
            this.nextWithdrawalProcessor.withdraw(atm, amount);
        }
    }

    public abstract void withdraw(ATM atm, int amount);
}
