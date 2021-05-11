package org.pastore.db.value;

import org.pastore.exception.client.ClientException;

public class IntegerDBValue extends DBValue<Integer> {

    public IntegerDBValue(Integer value) {
        super(value, DBValueType.INTEGER);
    }

    @Override
    public void increment() throws ClientException {
        this.setValue(this.getValue() + 1);
    }
}
