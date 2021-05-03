package org.pastore.db.value;

import java.util.List;

public class ListIntDBValue extends ListDBValue<Integer> {

    public ListIntDBValue(List<Integer> value) {
        super(value, DBValueType.LIST_INT);
    }
}
