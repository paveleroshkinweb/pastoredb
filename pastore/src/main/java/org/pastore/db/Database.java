package org.pastore.db;

import org.pastore.config.property.DatabasesProperty;
import org.pastore.config.property.DumpFileProperty;
import org.pastore.config.property.HistoryFileProperty;
import org.pastore.config.property.SaveIntervalProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Database {

    private static Database instance;

    private static ScheduledExecutorService executor;

    private List<Store> db;

    private DumpFileProperty dumpFile;

    private HistoryFileProperty historyFile;

    private DatabasesProperty databases;

    private SaveIntervalProperty saveInterval;

    private Database(List<Store> db,
                     DumpFileProperty dumpFile,
                     HistoryFileProperty historyFile,
                     DatabasesProperty databases,
                     SaveIntervalProperty saveInterval) {
        this.db = db;
        this.dumpFile = dumpFile;
        this.historyFile = historyFile;
        this.databases = databases;
        this.saveInterval = saveInterval;
    }

    private static List<Store> readDump(DumpFileProperty dumpFile, DatabasesProperty databases) {
        List<Store> db = new ArrayList<>();
        for (int i = 0; i < databases.getValue(); i++) {
            db.add(new Store(i));
        }
        return db;
    }

    public static void init(DumpFileProperty dumpFile,
                            HistoryFileProperty historyFile,
                            DatabasesProperty databases,
                            SaveIntervalProperty saveInterval) {
        List<Store> db = readDump(dumpFile, databases);
        instance = new Database(db, dumpFile, historyFile, databases, saveInterval);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public static Database getInstance() {
        return instance;
    }

    public Store getStoreByIndex(int index) {
        return this.db.get(index);
    }

    public void setExpires(String key, int expires, int storeNumber) {
        executor.schedule(new ExpireJob(key, storeNumber), expires, TimeUnit.SECONDS);
    }

    public void shutdownTimers() {
        executor.shutdownNow();
    }

    public int getStoresNumber() {
        return this.db.size();
    }
}
