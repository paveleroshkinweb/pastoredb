package org.pastore.db;

import java.util.List;

public class ListStrDBValue extends DBValue<List<String>>{

    public ListStrDBValue(List<String> value) {
        super(value, DBValueType.LIST_STR);
    }

}
