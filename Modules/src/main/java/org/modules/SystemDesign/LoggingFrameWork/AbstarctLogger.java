package org.modules.SystemDesign.LoggingFrameWork;

public abstract class AbstarctLogger {
    int level;

    AbstarctLogger nextLogger;

    public void setNextLogger(AbstarctLogger nextLogger) {
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
