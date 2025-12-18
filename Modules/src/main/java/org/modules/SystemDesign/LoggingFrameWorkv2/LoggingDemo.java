package org.modules.SystemDesign.LoggingFrameWorkv2;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();

        for(int i=1; i<=100; i++){
            logger.info(i + " info");
            logger.debug(i + " debug");
            logger.error(i + " error");
            try{
                Thread.sleep(100);
            } catch (InterruptedException ignored){}
        }
    }
}
