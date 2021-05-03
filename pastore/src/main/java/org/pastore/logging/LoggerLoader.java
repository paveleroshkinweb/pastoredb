package org.pastore.logging;


import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.pastore.config.property.LogFileProperty;
import org.pastore.config.property.LogLevelProperty;

public class LoggerLoader {

    private static boolean loaded = false;

    private LoggerLoader() {}

    public static void loadLogger(LogFileProperty logFile, LogLevelProperty logLevel) {
        if (! loaded) {
            String logFilePath = logFile.getValue();
            Level level = logLevel.getValue().getLevel();
            PatternLayout layout = new PatternLayout();
            String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
            layout.setConversionPattern(conversionPattern);
            FileAppender fileAppender = new FileAppender();
            fileAppender.setFile(logFilePath);
            fileAppender.setLayout(layout);
            fileAppender.activateOptions();
            Logger rootLogger = Logger.getRootLogger();
            rootLogger.setLevel(level);
            rootLogger.addAppender(fileAppender);
            loaded = true;
        }

    }

}
