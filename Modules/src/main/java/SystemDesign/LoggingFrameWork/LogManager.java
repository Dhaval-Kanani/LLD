package SystemDesign.LoggingFrameWork;

public class LogManager {
    protected static AbstarctLogger buildChainOfLogger(){
        AbstarctLogger infoLogger = new InfoLogger(1);
        AbstarctLogger errorLogger = new ErrorLogger(2);
        AbstarctLogger debugLogger = new DebugLogger(3);

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
