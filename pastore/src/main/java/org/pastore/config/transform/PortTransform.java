package org.pastore.config.transform;

import org.pastore.clientexception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class PortTransform implements ITransform<Integer>{

    @Override
    public Integer transform(ConfigProperty property, String plainValue, Integer defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        Integer port;
        try {
            port = Integer.parseInt(plainValue);
        } catch (NumberFormatException e) {
            throw new InvalidConfigPropertyException(property);
        }
        if (port >= 1024 && port <= 49151) {
            return port;
        }
        throw new InvalidConfigPropertyException(property);
    }
}
