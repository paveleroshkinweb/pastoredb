package org.pastore.db.value;

import org.pastore.exception.client.command.InvalidCommandException;

import java.util.List;

public class ListStrDBValue extends ListDBValue<StringDBValue> {

    public ListStrDBValue(List<StringDBValue> value) {
        super(value, DBValueType.LIST_STR);
    }

    @Override
    public StringDBValue cast(String value) throws InvalidCommandException {
        return new StringDBValue(value);
    }
}
