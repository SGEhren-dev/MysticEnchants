package com.ehrengrenlund.mysticenchants.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticLogger {
    private final Logger logger;
    private static MysticLogger instance;

    private static final String loggerPrefix = "[MysticEnchants]: ";

    private MysticLogger() {
        this.logger = LoggerFactory.getLogger("mysticenchants");
    }

    public static MysticLogger getInstance() {
        if (instance == null) {
            return new MysticLogger();
        }

        return instance;
    }

    private String getPrefixedMessage(String message) {
        return loggerPrefix + message;
    }

    public void info(String message) {
        logger.info(getPrefixedMessage(message));
    }

    public void warn(String message) {
        logger.info(getPrefixedMessage(message));
    }

    public void error(String message) {
        logger.error(getPrefixedMessage(message));
    }
}
