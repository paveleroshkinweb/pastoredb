package org.pastore.db;

public class DBValue<T> {

    private String propertyName;

    private T value;

    private DBValueType dbValueType;

    public DBValue(T value, DBValueType dbValueType) {
        this.value = value;
        this.dbValueType = dbValueType;
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
}
