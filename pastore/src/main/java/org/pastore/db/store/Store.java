package org.pastore.db.store;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.PropertyType;
import org.pastore.db.AbstractDatabase;
import org.pastore.db.value.DBValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private AbstractDatabase database;

    private int number;

    private Map<String, DBValue> store;

    public Store(Map<String, DBValue> store) {
        this.store = store;
    }

    public Store(final AbstractDatabase database, final int number) {
        this.database = database;
        this.store = new ConcurrentHashMap<>();
        this.number = number;
    }

    public DBValue getDBValueByKey(String key) {
        return this.store.get(key);
    }

    public boolean keyExists(String key) {
        return this.store.containsKey(key);
    }

    public void removeValueByKey(String key) {
        if (keyExists(key)) {
            this.store.remove(key);
        }
    }

    public void addDBValue(String key, DBValue dbValue, Integer expires) {
        this.store.put(key, dbValue);
        if (expires != null) {
            this.setExpires(key, expires);
        }
    }

    public void removeExpiredKey(String key) {
        DBValue value = this.getDBValueByKey(key);
        if (value != null && System.currentTimeMillis() >= value.getExpires()) {
            this.removeValueByKey(key);
            this.getDatabase().getHistoryHandler().writeCommand(createDeleteCommand(key));
        }
    }

    private static Command createDeleteCommand(String key) {
        Map<PropertyType, String> properties = new HashMap<>();
        properties.put(PropertyType.KEY, key);
        Command command = new Command(CommandType.DELETE, null, properties, null);
        return command;
    }

    public void setExpires(String key, int expires) {
        DBValue value = this.getDBValueByKey(key);
        if (value != null) {
            value.setExpires(expires);
            database.setExpires(key, expires, this.number);
        }
    }

    public int getNumber() {
        return this.number;
    }

    public AbstractDatabase getDatabase() {
        return database;
    }
}
