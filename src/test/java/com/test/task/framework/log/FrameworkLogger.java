package com.test.task.framework.log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrameworkLogger {
    private static Logger logger = LogManager.getLogger();
    public static void info(String message) {
        logger.info(message);
    }
    public static void error(String message) {
        logger.error(message);
    }
    public static void fatal(String message) {
        logger.fatal(message);
    }
}