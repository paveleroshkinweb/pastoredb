package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.logging.LogLevel;


public class LogLevelTransform implements ITransform<LogLevel> {

    private static final String ERROR = "Please make sure that " +
                                        ConfigProperty.LOG_LEVEL.getPropertyName() +
                                        " is one of " + LogLevel.availableLogLevels();

    @Override
    public LogLevel transform(ConfigProperty property, String plainValue, LogLevel defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        LogLevel logLevel = LogLevel.getLevelByName(plainValue);
        if (logLevel == null) {
            throw new InvalidConfigPropertyException(ERROR);
        }
        return logLevel;
    }
}
