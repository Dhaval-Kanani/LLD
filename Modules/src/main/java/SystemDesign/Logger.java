package SystemDesign;

import static SystemDesign.LogManager.buildChainOfLogger;
import static SystemDesign.LogManager.buildLogSubject;

public class Logger {
    private volatile static Logger logger;
    private volatile static AbstarctLogger chainOfLogger;
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
        chainOfLogger.logMessage(level, msg);
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
