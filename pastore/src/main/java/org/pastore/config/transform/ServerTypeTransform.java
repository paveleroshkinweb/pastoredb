package org.pastore.config.transform;

import org.pastore.exception.config.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.server.ServerType;

public class ServerTypeTransform implements ITransform<ServerType> {

    private static final String ERROR = "Please make sure that " + ConfigProperty.SERVER_TYPE.getPropertyName() +
                                        " is one of " + ServerType.availableServerTypes();
    @Override
    public ServerType transform(ConfigProperty property, String plainValue, ServerType defaultValue) throws InvalidConfigPropertyException {
        if (plainValue == null) {
            return defaultValue;
        }
        ServerType serverType = ServerType.getServerTypeByName(plainValue);
        if (serverType == null) {
            throw new InvalidConfigPropertyException(ERROR);
        }
        return serverType;
    }
}
