package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class IntegerTransform implements ITransform<Integer> {

    private static final String ERROR = "Please make sure that %s is a correct positive integer";

    @Override
    public Integer transform(ConfigProperty property, String plainValue, Integer defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        Integer value;
        try {
            value = Integer.parseInt(plainValue.trim());
        } catch (NumberFormatException e) {
            throw new InvalidConfigPropertyException(String.format(ERROR, property.getPropertyName()));
        }
        if (value <= 0) {
            throw new InvalidConfigPropertyException(String.format(ERROR, property.getPropertyName()));
        }
        return value;
    }
}
