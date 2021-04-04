package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class IntegerTransform implements ITransform<Integer> {

    @Override
    public Integer transform(ConfigProperty property, String plainValue, Integer defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        Integer value;
        try {
            value = Integer.parseInt(plainValue.trim());
        } catch (NumberFormatException e) {
            throw new InvalidConfigPropertyException(property);
        }
        if (value <= 0) {
            throw new InvalidConfigPropertyException(property);
        }
        return value;
    }
}
