package org.pastore.db.value;

import org.pastore.exception.client.ClientException;
import org.pastore.parse.StrUtils;

import java.util.List;

public class ListIntDBValue extends ListDBValue<IntegerDBValue> {

    public ListIntDBValue(List<IntegerDBValue> value) {
        super(value, DBValueType.LIST_INT);
    }

    @Override
    public IntegerDBValue cast(String value) throws ClientException {
        Integer intValue = StrUtils.parseStringToInt(value);
        return new IntegerDBValue(intValue);
    }
}
