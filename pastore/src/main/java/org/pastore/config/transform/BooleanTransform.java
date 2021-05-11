package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class BooleanTransform implements ITransform<Boolean> {

    private static final String ERROR = "Please make sure that %s is 0 or 1";

    @Override
    public Boolean transform(ConfigProperty property, String plainValue, Boolean defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        String value = plainValue.trim();
        if (value.equals("0")) {
            return false;
        }
        if (value.equals("1")) {
            return true;
        }
        throw new InvalidConfigPropertyException(String.format(ERROR, property.getPropertyName()));
    }

}
