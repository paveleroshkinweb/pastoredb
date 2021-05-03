package org.pastore.server;

import org.pastore.config.Loader;
import org.pastore.clientexception.config.InvalidConfigPropertyException;
import org.pastore.config.property.*;

import java.io.IOException;


public class ServerFactory {

    private ServerFactory() {}

    public static Server getServer(ServerType serverType) throws InvalidConfigPropertyException, IOException {
        BindProperty bindAddress = Loader.getBindProperty();
        PortProperty port = Loader.getPortProperty();
        MaxClientsProperty maxClients = Loader.getMaxClientsProperty();
        PasswordProtectedProperty isPasswordProtected = Loader.getPasswordProtectedProperty();
        BacklogProperty backlog = Loader.getBacklogProperty();
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
