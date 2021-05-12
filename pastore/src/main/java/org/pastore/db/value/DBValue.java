package org.pastore.db.value;

import org.pastore.command.CommandType;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.NotAllowedCommandException;

import java.util.concurrent.TimeUnit;

public abstract class DBValue<T> {

    private T value;

    private DBValueType dbValueType;

    private long expires;

    public DBValue(T value, DBValueType dbValueType) {
        this.value = value;
        this.dbValueType = dbValueType;
        this.expires = -1;
    }

    public T getValue() {
        return value;
    }

    public DBValueType getDbValueType() {
        return dbValueType;
    }

    public String toString() {
        return value.toString();
    }

    public String toResponse() {
        return dbValueType.getPrefix() + ":" + value.toString();
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void increment() throws ClientException {
        throw new NotAllowedCommandException(CommandType.INCREMENT, this.dbValueType);
    }

    public void setExpires(int seconds) {
        this.expires = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);
    }

    public long getExpires() {
        return expires;
    }

    public void push(String value) throws ClientException {
        throw new NotAllowedCommandException(CommandType.PUSH, this.dbValueType);
    }

    public String pop() throws ClientException {
        throw new NotAllowedCommandException(CommandType.POP, this.dbValueType);
    }

    public String shift() throws ClientException {
        throw new NotAllowedCommandException(CommandType.SHIFT, this.dbValueType);
    }

    public void unshift(String value) throws ClientException {
        throw new NotAllowedCommandException(CommandType.UNSHIFT, this.dbValueType);
    }

    public String size() throws ClientException {
        throw new NotAllowedCommandException(CommandType.SIZE, this.dbValueType);
    }

    public String index(int index) throws ClientException {
        throw new NotAllowedCommandException(CommandType.INDEX, this.dbValueType);
    }

}
