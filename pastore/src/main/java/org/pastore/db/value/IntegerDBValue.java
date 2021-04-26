package org.pastore.db.value;

import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;

public class IntegerDBValue extends DBValue<Integer> {

    public IntegerDBValue(Integer value) {
        super(value, DBValueType.INTEGER);
    }

}
