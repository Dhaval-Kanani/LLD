package org.modules.SystemDesign.LoggingFrameWorkv2;

public abstract class AbstractLogger {
    private AbstractLogger nextLogger;
    private int level;

    public AbstractLogger(int level) {
        this.level = level;
    }

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogMessage logMessage, LogSubject logSubject){
        if(this.level==logMessage.getLevel()){
            log(logMessage, logSubject);
        }
        if(nextLogger!=null){
            nextLogger.logMessage(logMessage, logSubject);
        }
    }

    protected abstract void log(LogMessage logMessage, LogSubject logSubject);
}
