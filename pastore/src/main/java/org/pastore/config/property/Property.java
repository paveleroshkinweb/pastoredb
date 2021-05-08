package org.pastore.config.property;

import org.pastore.config.Loader;
import org.pastore.config.transform.ITransform;


public class Property<T> {

    private final ConfigProperty configProperty;

    private final String plainValue;

    private final ITransform<T> transformator;

    private final T defaultValue;

    private T value;

    public Property(ConfigProperty configProperty, T defaultValue, ITransform<T> transformator) {
        this.configProperty = configProperty;
        this.plainValue = Loader.getPlainPropertyValue(configProperty.getPropertyName());
        this.defaultValue = defaultValue;
        this.transformator = transformator;
        this.value = null;
    }

    public T getValue() {
        if (this.value == null) {
            this.value = this.transformator.transform(this.configProperty, this.plainValue, this.defaultValue);
        }
        return this.value;
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
