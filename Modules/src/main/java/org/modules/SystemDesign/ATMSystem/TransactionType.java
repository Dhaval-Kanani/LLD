package org.modules.SystemDesign.ATMSystem;

public enum TransactionType {
    CASH_WITHDRAWAL,
    CHECK_BALANCE;

    public static void getAllOps(){
        for(TransactionType transactionType: TransactionType.values()){
            System.out.println(transactionType);
        }
    }
}
