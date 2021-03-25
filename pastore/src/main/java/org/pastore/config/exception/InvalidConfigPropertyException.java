package org.pastore.config.exception;

import org.pastore.config.property.ConfigProperty;
import org.pastore.config.property.LogLevelProperty;

import java.util.HashMap;
import java.util.Map;

public class InvalidConfigPropertyException extends Exception {

    private static final Map<ConfigProperty, String> errorMessages = new HashMap<>() {
        {
            put(ConfigProperty.LOGFILE,
                    "Please check that " + ConfigProperty.LOGFILE.getPropertyName() +
                    " exists and you can access it!");
            put(ConfigProperty.LOG_LEVEL,
                    "Please make sure that " + ConfigProperty.LOG_LEVEL.getPropertyName() +
                    " is one of " + LogLevelProperty.levels.keySet());
        }
    };

    public InvalidConfigPropertyException(ConfigProperty property) {
        super(errorMessages.get(property));
    }
}
