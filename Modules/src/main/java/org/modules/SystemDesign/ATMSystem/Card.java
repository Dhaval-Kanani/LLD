package org.modules.SystemDesign.ATMSystem;

public class Card {
    private String cardNo;
    private User user;
    private BankAccount bankAccount;
    private String cvv;
    private String expDate;
    private String pin;

    public Card(String cardNo, User user, BankAccount bankAccount, String cvv, String expDate, String pin) {
        this.cardNo = cardNo;
        this.user = user;
        this.bankAccount = bankAccount;
        this.cvv = cvv;
        this.expDate = expDate;
        this.pin = pin;
    }

    public User getUser() {
        return user;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public boolean checkEnteredPin(String enteredPin) {
        return pin.equals(enteredPin);
    }
}
