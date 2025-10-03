package org.modules.SystemDesign.LoggingFrameWork;

import static org.modules.SystemDesign.LoggingFrameWork.LogManager.buildChainOfLogger;
import static org.modules.SystemDesign.LoggingFrameWork.LogManager.buildLogSubject;

public class Logger {
    private volatile static Logger logger;
    private volatile static AbstractLogger chainOfLogger;
    private volatile static LogSubject logSubject;

    private Logger(){
        if(logger!=null){
            throw new IllegalStateException("Object already created.");
        }
    }

    public static Logger getInstance(){
        if(logger == null){
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger();
                    chainOfLogger = buildChainOfLogger();
                    logSubject = buildLogSubject();
                }
            }
        }
        return logger;
    }

    private void createMsg(int level, String msg){
        chainOfLogger.logMessage(level, msg, logSubject);
    }

    public void info(String msg){
        createMsg(1, msg);
    }

    public void error(String msg){
        createMsg(2, msg);
    }

    public void debug(String msg){
        createMsg(3, msg);
    }
}
