package org.modules.SystemDesign.ATMSystem;

public class User {
    private String name;
    private Card card;
    private BankAccount bankAccount;

    public User(String name, Card card, BankAccount bankAccount) {
        this.name = name;
        this.card = card;
        this.bankAccount = bankAccount;
    }

    public Card getCard() {
        return card;
    }
}
