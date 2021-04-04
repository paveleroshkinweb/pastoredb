package org.pastore.config.property;

import org.pastore.config.transform.LogLevelTransform;
import org.pastore.logging.LogLevel;

public class LogLevelProperty extends Property<LogLevel>{

    public LogLevelProperty() {
        super(ConfigProperty.LOG_LEVEL,
              LogLevel.INFO,
              new LogLevelTransform());
    }
}
