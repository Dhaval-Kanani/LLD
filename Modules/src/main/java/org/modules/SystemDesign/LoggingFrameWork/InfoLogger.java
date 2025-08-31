package org.modules.SystemDesign.LoggingFrameWork;

public class InfoLogger extends AbstarctLogger{
    public InfoLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        logSubject.notifyObservers(1, "INFO: " + msg);
//        System.out.println("Info: "+  msg);
    }
}
