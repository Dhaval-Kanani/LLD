package org.modules.SystemDesign.LoggingFrameWork;

public class Application {

    public static void main(String[] arg) {
        Logger logger = Logger.getInstance();
        logger.info("msg from info log");
        logger.error("msg from error log");
        logger.debug("msg from debug log");
    }
}
