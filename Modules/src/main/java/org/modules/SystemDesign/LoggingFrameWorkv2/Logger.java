package org.modules.SystemDesign.LoggingFrameWorkv2;

public class Logger {
    private static Logger logger;
    private static AbstractLogger abstractLogger;
    private static LogSubject logSubject;
    private static Object object = new Object();
    private Logger(){

    }

    public static Logger getLogger(){
        if(logger==null){
            synchronized (object){
                if(logger == null){
                    logger = new Logger();
                    abstractLogger = LogManager.buildChainOfLogger();
                    logSubject = LogManager.buildLogSubject();
                }
            }
        }
        return logger;
    }

    private void createMsg(int level, String msg){
        LogMessage logMessage = new LogMessage(level, msg);
        abstractLogger.logMessage(logMessage, logSubject);
    }

    public void info(String msg){
        createMsg(1, msg);
    }

    public void debug(String msg){
        createMsg(2,msg);
    }

    public void error(String msg){
        createMsg(3,msg);
    }
}
