package org.pastore.db.value;

import org.pastore.exception.client.command.EmptyListException;
import org.pastore.exception.client.command.IndexException;
import org.pastore.exception.client.command.InvalidCommandException;

import java.util.List;

public abstract class ListDBValue<T extends DBValue> extends DBValue<List<T>> {

    public ListDBValue(List<T> value, DBValueType dbValueType) {
        super(value, dbValueType);
    }

    @Override
    public void push(String value) throws InvalidCommandException {
        this.insert(value, this.getValue().size());
    }

    @Override
    public String pop() throws InvalidCommandException {
        return this.remove(this.getValue().size() - 1);
    }

    @Override
    public String shift() throws InvalidCommandException {
        return this.remove(0);
    }

    @Override
    public void unshift(String value) throws InvalidCommandException {
        this.insert(value, 0);
    }

    @Override
    public String size() throws InvalidCommandException {
        return DBValueType.INTEGER.getPrefix() + ":" + this.getValue().size();
    }

    @Override
    public String index(int index) throws InvalidCommandException {
        try {
            return this.getValue().get(index).toResponse();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    private void insert(String value, int index) throws InvalidCommandException {
        T valueToInsert = this.cast(value);
        this.getValue().add(index, valueToInsert);
    }

    private String remove(int index) throws InvalidCommandException {
        if (this.getValue().size() == 0) {
            throw new EmptyListException();
        }
        T element = this.getValue().remove(index);
        return element.toResponse();
    }

    public abstract T cast(String value) throws InvalidCommandException;
}
