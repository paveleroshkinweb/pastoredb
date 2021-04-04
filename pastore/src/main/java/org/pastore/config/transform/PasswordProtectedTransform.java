package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class PasswordProtectedTransform implements ITransform<Boolean> {

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
        throw new InvalidConfigPropertyException(property);
    }

}
