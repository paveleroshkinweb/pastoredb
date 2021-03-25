package org.pastore.config.property;

import java.util.HashMap;
import java.util.Map;

public class PropertyFactory {

    private static final Map<ConfigProperty, Property> instances = new HashMap<>() {
        {
            put(ConfigProperty.LOG_LEVEL, new LogLevelProperty());
            put(ConfigProperty.LOGFILE, new LogFileProperty());
        }
    };

    private PropertyFactory() {}

    public static Property getProperty(ConfigProperty property) {
        return instances.get(property);
    }
}
