package org.modules.SystemDesign.LoggingFrameWork;

public class LogManager {
    protected static AbstractLogger buildChainOfLogger(){
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger errorLogger = new ErrorLogger(2);
        AbstractLogger debugLogger = new DebugLogger(3);

        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);

        return infoLogger;
    }

    protected static LogSubject buildLogSubject(){
        LogSubject logSubject = new LogSubject();
        LogObserver consoleObserver = new ConsoleObserver();
        LogObserver fileObserver = new FileObserver();

        logSubject.addObserver(1, consoleObserver);
        logSubject.addObserver(2, consoleObserver);
        logSubject.addObserver(2, fileObserver);
        return logSubject;
    }
}
