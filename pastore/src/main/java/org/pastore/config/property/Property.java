package org.pastore.config.property;

import org.pastore.config.Loader;
import org.pastore.config.transform.ITransform;
import org.pastore.exception.config.InvalidConfigPropertyException;


public class Property<T> {

    private ConfigProperty configProperty;

    private String plainValue;

    private ITransform<T> transformator;

    private T defaultValue;

    private T value;

    public Property(ConfigProperty configProperty, T defaultValue, ITransform<T> transformator) {
        this.configProperty = configProperty;
        this.plainValue = Loader.getPlainPropertyValue(configProperty.getPropertyName());
        this.defaultValue = defaultValue;
        this.transformator = transformator;
        this.value = null;
    }

    public T getValue() {
        return this.value;
    }

    public void process() throws InvalidConfigPropertyException {
        this.value = this.transformator.transform(this.configProperty, this.plainValue, this.defaultValue);
    }

    public void setValue(T value) {
        this.value = value;
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
