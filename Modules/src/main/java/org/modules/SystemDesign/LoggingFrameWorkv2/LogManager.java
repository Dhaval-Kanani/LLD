package org.modules.SystemDesign.LoggingFrameWorkv2;

import org.modules.SystemDesign.LoggingFrameWorkv2.Observer.ConsoleObserver;
import org.modules.SystemDesign.LoggingFrameWorkv2.Observer.FileObserver;
import org.modules.SystemDesign.LoggingFrameWorkv2.Observer.LogObserver;

public class LogManager {
    protected static AbstractLogger buildChainOfLogger(){
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger debugLogger = new DebugLogger(2);
        AbstractLogger errorLogger = new ErrorLogger(3);

        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }

    protected static LogSubject buildLogSubject(){
        LogSubject logSubject = new LogSubject();

        LogObserver consoleObserver = new ConsoleObserver();
        LogObserver fileObserver = new FileObserver();

        logSubject.addObserver(1, consoleObserver);
        logSubject.addObserver(2, consoleObserver);
        logSubject.addObserver(3, consoleObserver);
        logSubject.addObserver(1, fileObserver);
        logSubject.addObserver(2, fileObserver);
        logSubject.addObserver(3, fileObserver);
        return logSubject;
    }
}
