package org.pastore.logging;


import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.*;


public class LoggerLoader {

    private static boolean loaded = false;

    private LoggerLoader() {}

    public static void loadLogger() throws InvalidConfigPropertyException {
        if (!loaded) {
            Property<String> logFileProperty = PropertyFactory.getProperty(ConfigProperty.LOGFILE);
            Property<LogLevel> levelProperty = PropertyFactory.getProperty(ConfigProperty.LOG_LEVEL);
            String filename = logFileProperty.getValue();
            Level logLevel = levelProperty.getValue().getLevel();
            PatternLayout layout = new PatternLayout();
            String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
            layout.setConversionPattern(conversionPattern);
            FileAppender fileAppender = new FileAppender();
            fileAppender.setFile(filename);
            fileAppender.setLayout(layout);
            fileAppender.activateOptions();
            Logger rootLogger = Logger.getRootLogger();
            rootLogger.setLevel(logLevel);
            rootLogger.addAppender(fileAppender);
            loaded = true;
        }
    }
}
