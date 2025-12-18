package org.modules.SystemDesign.LoggingFrameWorkv2;

public class InfoLogger extends AbstractLogger{

    InfoLogger(int level){
        super(level);
    }

    @Override
    protected void log(LogMessage logMessage, LogSubject logSubject) {
        logSubject.notifyObservers(logMessage);
    }
}
