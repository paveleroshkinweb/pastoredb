package org.pastore.config.property;

import org.apache.log4j.Logger;
import org.pastore.Main;
import org.pastore.config.ConfigLoader;
import org.pastore.config.transform.ITransform;
import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.logging.LoggerLoader;


public class Property<T> {

    private ConfigProperty configProperty;

    private String plainValue;

    private ITransform<T> transformator;

    private T defaultValue;

    private T cachedValue;

    public T getValue() {
        if (cachedValue != null) {
            return cachedValue;
        }
        try {
            T result = this.transformator.transform(this.configProperty, this.plainValue, this.defaultValue);
            cachedValue = result;
        } catch (InvalidConfigPropertyException e) {
            if (LoggerLoader.isLoaded()) {
                Logger logger = Logger.getLogger(Property.class);
                logger.error("Exception occurred", e);
            }
            System.exit(1);
        }
        return cachedValue;
    }

    public Property(ConfigProperty configProperty, T defaultValue, ITransform<T> transformator) {
        this.configProperty = configProperty;
        this.plainValue = ConfigLoader.getProperty(configProperty.getPropertyName());
        this.defaultValue = defaultValue;
        this.transformator = transformator;
    }

    public ConfigProperty getConfigProperty() {
        return this.configProperty;
    }

    public String getPlainValue() {
        return this.plainValue;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }
}
