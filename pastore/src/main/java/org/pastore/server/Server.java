package org.pastore.server;

import java.io.Closeable;

public abstract class Server implements Closeable {

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

    public Server(ServerBuilder builder) {
        this.serverType = builder.getServerType();
        this.bindAddress = builder.getBindAddress();
        this.port = builder.getPort();
        this.maxClients = builder.getMaxClients();
        this.dumpfile = builder.getDumpfile();
        this.historyfile = builder.getHistoryfile();
        this.isPasswordProtected = builder.isPasswordProtected();
        this.timeout = builder.getTimeout();
        this.backlog = builder.getBacklog();
        this.databases = builder.getDatabases();
        this.saveInterval = builder.getSaveInterval();
    }

    public abstract void listen();

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
}
