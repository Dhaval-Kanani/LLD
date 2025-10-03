package org.modules.SystemDesign.LoggingFrameWork;

public abstract class AbstractLogger {
    int level;

    AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    void logMessage(int level, String msg, LogSubject logSubject){
        if(level==this.level){
            display(msg, logSubject);
        }
        if(nextLogger!=null){
            nextLogger.logMessage(level, msg, logSubject);
        }
    }

    protected abstract void display(String msg, LogSubject logSubject);
}
