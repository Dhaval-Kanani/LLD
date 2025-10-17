package org.modules.SystemDesign.ATMSystem;

import org.modules.SystemDesign.ATMSystem.ATMStates.IdleState;

public class ATMRoom {
    ATM atm;
    User user;

    public static void main(String[] args) {
        ATMRoom atmRoom = new ATMRoom();
        atmRoom.atm = ATM.getAtmObject();
        atmRoom.initialize();

        Card card = atmRoom.user.getCard();

        atmRoom.atm.getAtmState().insertCard(atmRoom.atm, card);
        atmRoom.atm.getAtmState().authenticatePin(atmRoom.atm, card, "112233");

        atmRoom.atm.getAtmState().insertCard(atmRoom.atm, card);
        atmRoom.atm.getAtmState().authenticatePin(atmRoom.atm, card, "122334");
        atmRoom.atm.getAtmState().selectOperation(atmRoom.atm, TransactionType.CHECK_BALANCE);
        atmRoom.atm.getAtmState().checkBalance(atmRoom.atm, card);


        atmRoom.atm.getAtmState().insertCard(atmRoom.atm, card);
        atmRoom.atm.getAtmState().authenticatePin(atmRoom.atm, card, "122334");
        atmRoom.atm.getAtmState().selectOperation(atmRoom.atm, TransactionType.CASH_WITHDRAWAL);
        atmRoom.atm.getAtmState().withdrawBalance(atmRoom.atm, card, 6000);

        atmRoom.atm.getAtmState().insertCard(atmRoom.atm, card);
        atmRoom.atm.getAtmState().authenticatePin(atmRoom.atm, card, "122334");
        atmRoom.atm.getAtmState().selectOperation(atmRoom.atm, TransactionType.CASH_WITHDRAWAL);
        atmRoom.atm.getAtmState().withdrawBalance(atmRoom.atm, card, 4600);
    }

    private void initialize(){
        atm.setAtmState(new IdleState());
        atm.setTotalBalance(7000);
        atm.setNoOfTwoThousandNotes(3);
        atm.setNoOfFiveHundredNotes(1);
        atm.setNoOfHundredNotes(5);
        BankAccount bankAccount = createBankAcc();
        user = new User("User", createCard(bankAccount), bankAccount);
    }

    private BankAccount createBankAcc(){
        return new BankAccount(user, "A001", 5000);
    }

    private Card createCard(BankAccount bankAccount){
        return new Card("C001", user, bankAccount, "123", "03/31", "122334");
    }
}
