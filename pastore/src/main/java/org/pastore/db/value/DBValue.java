package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.connection.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DBValue<T> {

    private T value;

    private DBValueType dbValueType;

    private List<Connection> subscribers;

    public DBValue(T value, DBValueType dbValueType) {
        this.value = value;
        this.dbValueType = dbValueType;
        this.subscribers = Collections.synchronizedList(new ArrayList<>());
    }

    public T getValue() {
        return value;
    }

    public DBValueType getDbValueType() {
        return dbValueType;
    }

    public String toString() {
        return dbValueType.getPrefix() + ":" + value.toString();
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract void increment() throws InvalidCommandException;
}
