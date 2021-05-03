package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.command.CommandType;

import java.util.List;

public abstract class ListDBValue<T> extends DBValue<List<T>> {

    public ListDBValue(List<T> value, DBValueType dbValueType) {
        super(value, dbValueType);
    }

    @Override
    public void increment() throws InvalidCommandException {
        throw new InvalidCommandException(CommandType.INCREMENT.getName() + " is not allowed for lists!");
    }
}
