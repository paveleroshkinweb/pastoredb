package org.pastore.config.property;

import org.pastore.config.ConfigLoader;
import org.pastore.config.transform.ITransform;
import org.pastore.config.transform.LogFileTransform;

public class LogFileProperty extends Property<String> {

    private static final ConfigProperty property = ConfigProperty.LOGFILE;

    private static final ITransform transformator = new LogFileTransform();

    private static final String defaultValue = "/var/log/pastore/pastore.log";

    private static final String plainValue = ConfigLoader.getProperty(property.getPropertyName());

    public LogFileProperty() {
        super(property, plainValue, defaultValue, transformator);
    }
}
