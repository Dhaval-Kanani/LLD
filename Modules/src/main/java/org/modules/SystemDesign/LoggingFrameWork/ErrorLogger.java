package org.modules.SystemDesign.LoggingFrameWork;

public class ErrorLogger extends AbstarctLogger{
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        logSubject.notifyObservers(2, "ERROR: " + msg);
//        System.out.println("Error: "+  msg);
    }
}
