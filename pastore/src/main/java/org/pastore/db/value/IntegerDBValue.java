package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;

public class IntegerDBValue extends DBValue<Integer> {

    public IntegerDBValue(Integer value) {
        super(value, DBValueType.INTEGER);
    }

    @Override
    public void increment() throws InvalidCommandException {
        this.setValue(this.getValue() + 1);
    }
}
