package org.modules.SystemDesign.LoggingFrameWorkv2;

public class ErrorLogger extends AbstractLogger{

    ErrorLogger(int level){
        super(level);
    }

    @Override
    protected void log(LogMessage logMessage, LogSubject logSubject) {
        logSubject.notifyObservers(logMessage);
    }
}
