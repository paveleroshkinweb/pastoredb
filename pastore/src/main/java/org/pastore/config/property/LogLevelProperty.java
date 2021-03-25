package org.pastore.config.property;

import org.apache.log4j.Level;
import org.pastore.config.ConfigLoader;
import org.pastore.config.transform.ITransform;
import org.pastore.config.transform.LogLevelTransform;

import java.util.HashMap;
import java.util.Map;

public class LogLevelProperty extends Property<Level>{

    private static final ConfigProperty property = ConfigProperty.LOG_LEVEL;

    private static final ITransform transformator = new LogLevelTransform();

    private static final Level defaultValue = Level.INFO;

    private static final String plainValue = ConfigLoader.getProperty(property.getPropertyName());

    public static final Map<String, Level> levels = new HashMap<>(){
        {
            put("debug", Level.DEBUG);
            put("info", Level.INFO);
            put("warn", Level.WARN);
            put("error", Level.ERROR);
        }
    };

    public LogLevelProperty() {
        super(property, plainValue, defaultValue, transformator);
    }
}
