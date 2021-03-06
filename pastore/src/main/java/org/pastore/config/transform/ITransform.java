package org.pastore.config.transform;

import org.pastore.config.property.ConfigProperty;
import org.pastore.exception.config.InvalidConfigPropertyException;

public interface ITransform<T> {

    T transform(ConfigProperty property, String plainValue, T defaultValue) throws InvalidConfigPropertyException;

}
