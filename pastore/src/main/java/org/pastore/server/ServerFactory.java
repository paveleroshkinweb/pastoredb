package org.pastore.server;

import org.pastore.config.property.*;
import org.pastore.exception.config.InvalidConfigPropertyException;

import java.io.IOException;


public class ServerFactory {

    private ServerFactory() {}

    public static Server getServer(ServerType serverType) throws InvalidConfigPropertyException, IOException {
        BindProperty bindAddress = new BindProperty();
        PortProperty port = new PortProperty();
        MaxClientsProperty maxClients = new MaxClientsProperty();
        PasswordProtectedProperty isPasswordProtected = new PasswordProtectedProperty();
        BacklogProperty backlog = new BacklogProperty();
        HistoryFileProperty historyFile = new HistoryFileProperty();
        DatabasesProperty databases = new DatabasesProperty();
        return new ServerBuilder()
                    .setServerType(serverType)
                    .setBindAddress(bindAddress)
                    .setPort(port)
                    .setMaxClients(maxClients)
                    .setPasswordProtected(isPasswordProtected)
                    .setBacklog(backlog)
                    .setHistoryFile(historyFile)
                    .setDatabases(databases)
                    .build();
    }
}
