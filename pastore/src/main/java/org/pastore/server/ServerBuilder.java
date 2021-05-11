package org.pastore.server;


import org.pastore.config.property.*;
import org.pastore.exception.common.NotImplementedException;

import java.io.IOException;

public class ServerBuilder {

    private ServerType serverType;

    private BindProperty bindAddress;

    private PortProperty port;

    private MaxClientsProperty maxClients;

    private PasswordProtectedProperty isPasswordProtected;

    private BacklogProperty backlog;

    private HistoryFileProperty historyFile;

    private DatabasesProperty databases;

    public ServerBuilder setServerType(ServerType serverType) {
        this.serverType = serverType;
        return this;
    }

    public ServerBuilder setBindAddress(BindProperty bindAddress) {
        this.bindAddress = bindAddress;
        return this;
    }

    public ServerBuilder setPort(PortProperty port) {
        this.port = port;
        return this;
    }

    public ServerBuilder setMaxClients(MaxClientsProperty maxClients) {
        this.maxClients = maxClients;
        return this;
    }

    public ServerBuilder setPasswordProtected(PasswordProtectedProperty passwordProtected) {
        isPasswordProtected = passwordProtected;
        return this;
    }

    public ServerBuilder setBacklog(BacklogProperty backlog) {
        this.backlog = backlog;
        return this;
    }

    public ServerBuilder setHistoryFile(HistoryFileProperty historyFile) {
        this.historyFile = historyFile;
        return this;
    }

    public ServerBuilder setDatabases(DatabasesProperty databases) {
        this.databases = databases;
        return this;
    }

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

    public Server build() throws NotImplementedException, IOException {
        if (this.serverType == ServerType.UNENCRYPTED) {
            return new UnencryptedServer(this);
        }
        throw new NotImplementedException(this.serverType.getName() + " is not implemented yet!");
    }
}
