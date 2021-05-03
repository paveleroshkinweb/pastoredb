package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;

import java.util.List;

public abstract class ListDBValue<T> extends DBValue<List<T>> {

    public ListDBValue(List<T> value, DBValueType dbValueType) {
        super(value, dbValueType);
    }

    @Override
    public void push(String value) throws InvalidCommandException {
        T valueToPush = this.cast(value);
        this.getValue().add(valueToPush);
    }

    @Override
    public String pop() throws InvalidCommandException {
        if (this.getValue().size() == 0) {
            throw new InvalidCommandException("List is empty list!");
        }
        T lastElement = this.getValue().remove(this.getValue().size() - 1);
        return lastElement.toString();
    }

    public abstract T cast(String value) throws InvalidCommandException;
}
