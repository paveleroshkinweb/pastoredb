package org.pastore.config.transform;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

public class MaxClientsTransform implements ITransform<Integer>{

    @Override
    public Integer transform(ConfigProperty property, String plainValue, Integer defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        Integer maxClients;
        try {
            maxClients = Integer.parseInt(plainValue);
        } catch (NumberFormatException e) {
            throw new InvalidConfigPropertyException(property);
        }
        if (maxClients <= 0) {
            throw new InvalidConfigPropertyException(property);
        }
        return maxClients;
    }
}
