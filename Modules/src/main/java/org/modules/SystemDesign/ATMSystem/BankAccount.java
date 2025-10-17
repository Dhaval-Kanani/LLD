package org.modules.SystemDesign.ATMSystem;

public class BankAccount {
    private User user;
    private String accountNo;
    private int amount;

    public BankAccount(User user, String accountNo, int amount) {
        this.user = user;
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
