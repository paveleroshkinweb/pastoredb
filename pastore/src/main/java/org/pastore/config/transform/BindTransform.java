package org.pastore.config.transform;

import org.pastore.clientexception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class BindTransform implements ITransform<String>{

    @Override
    public String transform(ConfigProperty property, String plainValue, String defaultValue)
            throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        if (!isValidInet4Address(plainValue)) {
            throw new InvalidConfigPropertyException(property);
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
