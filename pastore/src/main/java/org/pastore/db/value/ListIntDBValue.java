package org.pastore.db.value;

import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;

import java.util.List;

public class ListIntDBValue extends DBValue<List<Integer>> {

    public ListIntDBValue(List<Integer> value) {
        super(value, DBValueType.LIST_INT);
    }
}
