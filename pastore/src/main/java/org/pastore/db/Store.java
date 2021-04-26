package org.pastore.db;

import org.pastore.db.value.DBValue;

import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private ConcurrentHashMap<String, DBValue> store;

    public Store(ConcurrentHashMap<String, DBValue> store) {
        this.store = store;
    }

    public Store() {}
}
