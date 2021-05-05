package org.pastore.db.value;

import org.pastore.command.CommandType;
import org.pastore.exception.command.InvalidCommandException;

public abstract class DBValue<T> {

    private static final String INVALID_COMMAND = "%s is not allowed for type %s";

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
        return value.toString();
    }

    public String toResponse() {
        return dbValueType.getPrefix() + ":" + value.toString();
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void increment() throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                    INVALID_COMMAND,
                    CommandType.INCREMENT.getName(),
                    this.dbValueType.getPrefix()));
    }

    public void push(String value) throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.PUSH.getName(),
                this.dbValueType.getPrefix()));
    }

    public String pop() throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.POP.getName(),
                this.dbValueType.getPrefix()));
    }

    public String shift() throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.SHIFT.getName(),
                this.dbValueType.getPrefix()));
    }

    public void unshift(String value) throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.UNSHIFT.getName(),
                this.dbValueType.getPrefix()));
    }

    public String size() throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.SIZE.getName(),
                this.dbValueType.getPrefix()));
    }

    public String index(int index) throws InvalidCommandException {
        throw new InvalidCommandException(String.format(
                INVALID_COMMAND,
                CommandType.INDEX.getName(),
                this.dbValueType.getPrefix()));
    }
}
