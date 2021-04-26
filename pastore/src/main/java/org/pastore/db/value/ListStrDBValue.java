package org.pastore.db.value;

import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;

import java.util.List;

public class ListStrDBValue extends DBValue<List<String>> {

    public ListStrDBValue(List<String> value) {
        super(value, DBValueType.LIST_STR);
    }

}
