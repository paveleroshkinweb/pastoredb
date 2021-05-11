package org.pastore.db;

import org.pastore.config.property.DatabasesProperty;
import org.pastore.config.property.HistoryFileProperty;
import org.pastore.db.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Database implements IDatabase {

    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private List<Store> db;

    private HistoryFileProperty historyFile;

    private DatabasesProperty databases;

    public Database(HistoryFileProperty historyFile, DatabasesProperty databases) {
        this.historyFile = historyFile;
        this.databases = databases;
        this.init();
    }

    private void init() {
        this.db = new ArrayList<>();
        for (int i = 0; i < databases.getValue(); i++) {
            db.add(new Store(this, i));
        }
    }

    public Store getStoreByIndex(int index) {
        return this.db.get(index);
    }

    public void setExpires(String key, int expires, int storeNumber) {
        executor.schedule(new ExpireJob(this.getStoreByIndex(storeNumber), key), expires, TimeUnit.SECONDS);
    }

    public void shutdownTimers() {
        executor.shutdownNow();
    }

    public int getStoresNumber() {
        return this.db.size();
    }

    @Override
    public void close()  {
      this.shutdownTimers();
    }
}
