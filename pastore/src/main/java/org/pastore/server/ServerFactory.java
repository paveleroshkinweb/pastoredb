package org.pastore.server;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.*;
import org.pastore.config.transform.PortTransform;

import java.io.IOException;


public class ServerFactory {

    private ServerFactory() {}

    public static Server getServer(ServerType serverType) throws InvalidConfigPropertyException, IOException {
        BindProperty bindAddress = (BindProperty) PropertyFactory.getProperty(ConfigProperty.BIND);
        PortProperty port = (PortProperty) PropertyFactory.getProperty(ConfigProperty.PORT);
        MaxClientsProperty maxClients = (MaxClientsProperty) PropertyFactory.getProperty(ConfigProperty.MAX_CLIENTS);
        PasswordProtectedProperty isPasswordProtected = (PasswordProtectedProperty) PropertyFactory.getProperty(ConfigProperty.PASSWORD_PROTECTED);
        BacklogProperty backlog = (BacklogProperty) PropertyFactory.getProperty(ConfigProperty.TCP_BACKLOG);
        return new ServerBuilder()
                    .setServerType(serverType)
                    .setBindAddress(bindAddress)
                    .setPort(port)
                    .setMaxClients(maxClients)
                    .setPasswordProtected(isPasswordProtected)
                    .setBacklog(backlog)
                    .build();
    }
}
