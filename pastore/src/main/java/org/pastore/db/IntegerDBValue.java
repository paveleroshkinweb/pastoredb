package org.pastore.db;

public class IntegerDBValue extends DBValue<Integer> {

    public IntegerDBValue(Integer value) {
        super(value, DBValueType.INTEGER);
    }

}
