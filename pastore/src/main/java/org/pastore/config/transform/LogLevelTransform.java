package org.pastore.config.transform;

import org.apache.log4j.Level;
import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.config.property.LogLevelProperty;


public class LogLevelTransform implements ITransform<Level> {

    @Override
    public Level transform(ConfigProperty property, String plainValue, Level defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        if (!LogLevelProperty.levels.containsKey(plainValue)) {
            throw new InvalidConfigPropertyException(property);
        }
        return LogLevelProperty.levels.get(plainValue);
    }
}
