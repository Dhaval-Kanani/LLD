package org.modules.SystemDesign.ATMSystem.WithdrawalSys;

public class WithdrawalManager {
    public static WithdrawalProcessor getWithdrawalProcessor(){
        WithdrawalProcessor twoThousandProcessor = new TwoThousandProcessor();
        WithdrawalProcessor fiveHundredProcessor = new FiveHundredProcessor();
        WithdrawalProcessor oneHundredProcessor = new OneHundredProcessor();

        twoThousandProcessor.setNextWithdrawalProcessor(fiveHundredProcessor);
        fiveHundredProcessor.setNextWithdrawalProcessor(oneHundredProcessor);
        return twoThousandProcessor;
    }
}
