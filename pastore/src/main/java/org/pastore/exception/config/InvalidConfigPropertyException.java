package org.pastore.exception.config;

import org.pastore.config.property.ConfigProperty;
import org.pastore.logging.LogLevel;
import org.pastore.server.ServerType;

import java.util.HashMap;
import java.util.Map;

public class InvalidConfigPropertyException extends ConfigException {

    private static final Map<ConfigProperty, String> errorMessages = new HashMap<>() {
        {
            put(ConfigProperty.LOGFILE,
                    "Please check that " + ConfigProperty.LOGFILE.getPropertyName() +
                    " exists and you can access it!");
            put(ConfigProperty.LOG_LEVEL,
                    "Please make sure that " + ConfigProperty.LOG_LEVEL.getPropertyName() +
                    " is one of " + LogLevel.availableLogLevels());
            put(ConfigProperty.SERVER_TYPE,
                    "Please make sure that " + ConfigProperty.SERVER_TYPE.getPropertyName() +
                    " is one of " + ServerType.availableServerTypes());
            put(ConfigProperty.BIND,
                    "Please make sure that " + ConfigProperty.BIND.getPropertyName() +
                    "is a correct IPv4 address");
            put(ConfigProperty.PORT,
                    "Please make sure that " + ConfigProperty.PORT.getPropertyName() +
                            "is a correct integer in range 1024 ... 49151");
            put(ConfigProperty.MAX_CLIENTS,
                    "Please make sure that " + ConfigProperty.MAX_CLIENTS.getPropertyName() +
                            "is a correct positive integer");
            put(ConfigProperty.DUMPFILE,
                    "Please make sure that " + ConfigProperty.DUMPFILE.getPropertyName() +
                            " exists and you can access it!");
            put(ConfigProperty.HISTORYFILE,
                    "Please make sure that " + ConfigProperty.HISTORYFILE.getPropertyName() +
                            " exists and you can access it!");
            put(ConfigProperty.PASSWORD_PROTECTED,
                    "Please make sure that " + ConfigProperty.PASSWORD_PROTECTED.getPropertyName() +
                            " is 0 or 1");
            put(ConfigProperty.TCP_BACKLOG,
                    "Please make sure that " + ConfigProperty.TCP_BACKLOG.getPropertyName() +
                            "is a correct positive integer");
            put(ConfigProperty.DATABASES,
                    "Please make sure that " + ConfigProperty.DATABASES.getPropertyName() +
                            "is a correct positive integer");
            put(ConfigProperty.SAVE,
                    "Please make sure that " + ConfigProperty.SAVE.getPropertyName() +
                            "is a correct positive integer");
        }
    };

    public InvalidConfigPropertyException(ConfigProperty property) {
        super(errorMessages.get(property));
    }
}
