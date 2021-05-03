package org.pastore.config.transform;

import org.pastore.clientexception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.server.ServerType;

public class ServerTypeTransform implements ITransform<ServerType> {

    @Override
    public ServerType transform(ConfigProperty property, String plainValue, ServerType defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        ServerType serverType = ServerType.getServerTypeByName(plainValue);
        if (serverType == null) {
            throw new InvalidConfigPropertyException(property);
        }
        return serverType;
    }
}
