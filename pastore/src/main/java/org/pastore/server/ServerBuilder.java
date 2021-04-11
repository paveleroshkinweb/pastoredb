package org.pastore.server;


import org.pastore.utils.NotImplementedException;

import java.io.IOException;

public class ServerBuilder {

    private ServerType serverType;

    private String bindAddress;

    private int port;

    private int maxClients;

    private String dumpfile;

    private String historyfile;

    private boolean isPasswordProtected;

    private int timeout;

    private int backlog;

    private int databases;

    private int saveInterval;

    public ServerBuilder setServerType(ServerType serverType) {
        this.serverType = serverType;
        return this;
    }

    public ServerBuilder setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
        return this;
    }

    public ServerBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public ServerBuilder setMaxClients(int maxClients) {
        this.maxClients = maxClients;
        return this;
    }

    public ServerBuilder setDumpfile(String dumpfile) {
        this.dumpfile = dumpfile;
        return this;
    }

    public ServerBuilder setHistoryfile(String historyfile) {
        this.historyfile = historyfile;
        return this;
    }

    public ServerBuilder setPasswordProtected(boolean passwordProtected) {
        isPasswordProtected = passwordProtected;
        return this;
    }

    public ServerBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public ServerBuilder setBacklog(int backlog) {
        this.backlog = backlog;
        return this;
    }

    public ServerBuilder setDatabases(int databases) {
        this.databases = databases;
        return this;
    }

    public ServerBuilder setSaveInterval(int saveInterval) {
        this.saveInterval = saveInterval;
        return this;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public String getBindAddress() {
        return bindAddress;
    }

    public int getPort() {
        return port;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public String getDumpfile() {
        return dumpfile;
    }

    public String getHistoryfile() {
        return historyfile;
    }

    public boolean isPasswordProtected() {
        return isPasswordProtected;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getBacklog() {
        return backlog;
    }

    public int getDatabases() {
        return databases;
    }

    public int getSaveInterval() {
        return saveInterval;
    }

    public Server build() throws NotImplementedException, IOException {
        if (this.serverType == ServerType.UNENCRYPTED) {
            return new UnencryptedServer(this);
        }
        throw new NotImplementedException(this.serverType.getName() + " is not implemented yet!");
    }
}
