package org.pastore.server.worker;

import org.pastore.connection.Connection;
import org.pastore.db.IDatabase;
import org.pastore.handle.factory.IHandlerFactory;
import org.pastore.server.middleware.Middleware;

public abstract class AbstractWorker implements Runnable {

    private Connection connection;

    private String plainCommand;

    private Middleware middleware;

    private IDatabase database;

    public AbstractWorker(IDatabase database, Connection connection, String plainCommand, Middleware middleware) {
        this.connection = connection;
        this.plainCommand = plainCommand;
        this.middleware = middleware;
        this.database = database;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getPlainCommand() {
        return plainCommand;
    }

    public IHandlerFactory getHandlerFactory() {
        return this.database.getHandlerFactory();
    }

    public Middleware getMiddleware() {
        return middleware;
    }

    public IDatabase getDatabase() {
        return database;
    }
}
