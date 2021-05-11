package org.pastore.server;

import org.pastore.config.property.*;

import java.io.Closeable;

public abstract class Server implements Closeable {

    private ServerType serverType;

    private BindProperty bindAddress;

    private PortProperty port;

    private MaxClientsProperty maxClients;

    private PasswordProtectedProperty isPasswordProtected;

    private BacklogProperty backlog;

    private HistoryFileProperty historyFile;

    private DatabasesProperty databases;

    public Server(ServerBuilder builder) {
        this.serverType = builder.getServerType();
        this.bindAddress = builder.getBindAddress();
        this.port = builder.getPort();
        this.maxClients = builder.getMaxClients();
        this.isPasswordProtected = builder.isPasswordProtected();
        this.backlog = builder.getBacklog();
        this.historyFile = builder.getHistoryFile();
        this.databases = builder.getDatabases();
    }

    public abstract void listen();

    public ServerType getServerType() {
        return serverType;
    }

    public BindProperty getBindAddress() {
        return bindAddress;
    }

    public PortProperty getPort() {
        return port;
    }

    public MaxClientsProperty getMaxClients() {
        return maxClients;
    }

    public PasswordProtectedProperty isPasswordProtected() {
        return isPasswordProtected;
    }

    public BacklogProperty getBacklog() {
        return backlog;
    }

    public HistoryFileProperty getHistoryFile() {
        return historyFile;
    }

    public DatabasesProperty getDatabases() {
        return databases;
    }
}
