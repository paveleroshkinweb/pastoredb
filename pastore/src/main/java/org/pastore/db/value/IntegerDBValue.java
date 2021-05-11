package org.pastore.db.value;

import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.InvalidIntegerException;

public class IntegerDBValue extends DBValue<Integer> {

    public IntegerDBValue(Integer value) {
        super(value, DBValueType.INTEGER);
    }

    @Override
    public void increment() throws ClientException {
        if (this.getValue() == Integer.MAX_VALUE) {
            throw new InvalidIntegerException("Max int value achieved!");
        }
        this.setValue(this.getValue() + 1);

    }

}
