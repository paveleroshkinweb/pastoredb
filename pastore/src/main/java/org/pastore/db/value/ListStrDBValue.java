package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;

import java.util.List;

public class ListStrDBValue extends ListDBValue<String> {

    public ListStrDBValue(List<String> value) {
        super(value, DBValueType.LIST_STR);
    }

    @Override
    public String cast(String value) throws InvalidCommandException {
        return value;
    }
}
