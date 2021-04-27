package org.pastore.db;

import org.pastore.config.property.DatabasesProperty;
import org.pastore.config.property.DumpFileProperty;
import org.pastore.config.property.HistoryFileProperty;
import org.pastore.config.property.SaveIntervalProperty;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

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
        for (int i = 0; i < databases.getValue(); i ++) {
            db.add(new Store());
        }
        return db;
    }

    public static void init(DumpFileProperty dumpFile,
                            HistoryFileProperty historyFile,
                            DatabasesProperty databases,
                            SaveIntervalProperty saveInterval) {
        List<Store> db = readDump(dumpFile, databases);
        instance = new Database(db, dumpFile, historyFile, databases, saveInterval);
    }

    public static Database getInstance() {
        return instance;
    }

    public Store getStoreByIndex(int index) {
        return this.db.get(index);
    }
}
