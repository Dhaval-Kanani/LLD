package org.modules.SystemDesign.LoggingFrameWork;

public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg, LogSubject logSubject) {
        logSubject.notifyObservers(3, "DEBUG: " + msg);
//        System.out.println("Debug: "+  msg);
    }
}
