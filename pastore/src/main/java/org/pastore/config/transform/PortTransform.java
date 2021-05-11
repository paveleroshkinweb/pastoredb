package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class PortTransform implements ITransform<Integer> {

    private static final String ERROR = "Please make sure that " + ConfigProperty.PORT.getPropertyName() +
                                        " is a correct integer in range 1024 ... 49151";

    @Override
    public Integer transform(ConfigProperty property, String plainValue, Integer defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        Integer port;
        try {
            port = Integer.parseInt(plainValue);
        } catch (NumberFormatException e) {
            throw new InvalidConfigPropertyException(ERROR);
        }
        if (port >= 1024 && port <= 49151) {
            return port;
        }
        throw new InvalidConfigPropertyException(ERROR);
    }
}
