package org.pastore.config.transform;

import org.pastore.clientexception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.logging.LogLevel;


public class LogLevelTransform implements ITransform<LogLevel> {

    @Override
    public LogLevel transform(ConfigProperty property, String plainValue, LogLevel defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        LogLevel logLevel = LogLevel.getLevelByName(plainValue);
        if (logLevel == null) {
            throw new InvalidConfigPropertyException(property);
        }
        return logLevel;
    }
}
