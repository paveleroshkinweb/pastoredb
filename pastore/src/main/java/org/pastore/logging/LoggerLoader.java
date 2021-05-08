package org.pastore.logging;


import org.apache.log4j.*;
import org.pastore.config.property.LogFileProperty;
import org.pastore.config.property.LogLevelProperty;
import org.pastore.exception.logger.LoggerLoadedException;

public class LoggerLoader {

    private static final String IS_LOADED_ERROR = "%s already loaded! You should call it only once!";

    private static final String PATTERN = "%-7p %d [%t] %c %x - %m%n";

    private static boolean isLoaded = false;

    private static boolean isDefaultLoaded = false;

    private LoggerLoader() {}

    public static void loadLogger(LogFileProperty logFile, LogLevelProperty logLevel) {
        if (isLoaded) {
            throw new LoggerLoadedException(String.format(IS_LOADED_ERROR, "Config"));
        }
        String logFilePath = logFile.getValue();
        Level level = logLevel.getValue().getLevel();
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(PATTERN);
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(logFilePath);
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(level);
        rootLogger.addAppender(fileAppender);
        isLoaded = true;
    }

    public static void loadDefaultLogger() {
        if (isDefaultLoaded) {
            throw new LoggerLoadedException(String.format(IS_LOADED_ERROR, "Default config"));
        }
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(PATTERN);
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(LogLevel.DEBUG.getLevel());
        rootLogger.addAppender(consoleAppender);
        isDefaultLoaded = true;
    }

}
