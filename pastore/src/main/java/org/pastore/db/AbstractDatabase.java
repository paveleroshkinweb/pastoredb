package org.pastore.db;

import org.pastore.config.property.DatabasesProperty;
import org.pastore.db.store.Store;
import org.pastore.handle.factory.IHandlerFactory;
import org.pastore.history.AbstractHistoryHandler;

import java.io.Closeable;

public abstract class AbstractDatabase implements Closeable {

    private AbstractHistoryHandler historyHandler;

    private DatabasesProperty databases;

    private IHandlerFactory handlerFactory;

    public AbstractDatabase(AbstractHistoryHandler historyHandler, DatabasesProperty databases, IHandlerFactory handlerFactory) {
        this.historyHandler = historyHandler;
        this.databases = databases;
        this.handlerFactory = handlerFactory;
    }

    public AbstractHistoryHandler getHistoryHandler() {
        return historyHandler;
    }

    public DatabasesProperty getDatabases() {
        return databases;
    }

    public IHandlerFactory getHandlerFactory() {
        return handlerFactory;
    }

    public abstract Store getStoreByIndex(int index);

    public abstract void setExpires(String key, int expires, int storeNumber);

    public abstract void shutdownTimers();

    public abstract int getStoresNumber();

}
