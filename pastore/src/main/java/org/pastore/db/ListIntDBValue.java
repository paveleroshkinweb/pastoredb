package org.pastore.db;

import java.util.List;

public class ListIntDBValue extends DBValue<List<Integer>> {

    public ListIntDBValue(List<Integer> value) {
        super(value, DBValueType.LIST_INT);
    }
}
