package org.pastore.db;

import org.pastore.db.value.DBValue;

import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private ConcurrentHashMap<String, DBValue> store;

    public Store(ConcurrentHashMap<String, DBValue> store) {
        this.store = store;
    }

    public Store() {
        this.store = new ConcurrentHashMap<>();
    }

    public DBValue getDBValueByName(String name) {
        return this.store.get(name);
    }

    public boolean keyExists(String key) {
        return this.store.containsKey(key);
    }

    public void addDBValue(String property, DBValue dbValue) {
        this.store.put(property, dbValue);
    }
}
