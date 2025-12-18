package org.modules.SystemDesign.LoggingFrameWorkv2;

public class DebugLogger extends AbstractLogger{
    public DebugLogger(int level) {
        super(level);
    }

    @Override
    protected void log(LogMessage logMessage, LogSubject logSubject) {
        logSubject.notifyObservers(logMessage);
    }
}
