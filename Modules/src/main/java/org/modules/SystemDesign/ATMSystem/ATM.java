package org.modules.SystemDesign.ATMSystem;

import org.modules.SystemDesign.ATMSystem.ATMStates.ATMState;
import org.modules.SystemDesign.ATMSystem.ATMStates.IdleState;

public class ATM {
    private static ATM atmObject = new ATM();
    private ATMState atmState;

    private int totalBalance;
    private int noOfTwoThousandNotes;
    private int noOfFiveHundredNotes;
    private int noOfHundredNotes;

    private ATM(){
    }

    public static ATM getAtmObject(){
        atmObject.setAtmState(new IdleState());
        return atmObject;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public ATMState getAtmState() {
        return atmState;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getNoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }

    public void setNoOfTwoThousandNotes(int noOfTwoThousandNotes) {
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }

    public void setNoOfFiveHundredNotes(int noOfFiveHundredNotes) {
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
    }

    public int getNoOfHundredNotes() {
        return noOfHundredNotes;
    }

    public void setNoOfHundredNotes(int noOfHundredNotes) {
        this.noOfHundredNotes = noOfHundredNotes;
    }

    public void status(){
        System.out.println("Total Bal - " + totalBalance + " Two thousand notes - " + noOfTwoThousandNotes
         + " Five hundred notes - " + noOfFiveHundredNotes + " One Hundred notes - " + noOfHundredNotes);
    }
}
