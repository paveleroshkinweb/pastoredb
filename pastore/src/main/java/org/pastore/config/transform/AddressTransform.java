package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class AddressTransform implements ITransform<String> {

    private static final String ERROR = "Please make sure that %s is a correct IPv4 address";

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue)
            throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        if (!isValidInet4Address(plainValue)) {
            throw new InvalidConfigPropertyException(String.format(ERROR, property.getPropertyName()));
        }
        return plainValue;
    }

    private boolean isValidInet4Address(String ip) {
        try {
            return Inet4Address.getByName(ip).getHostAddress().equals(ip);
        }
        catch (UnknownHostException ex) {
            return false;
        }
    }
}
