package org.pastore.db.value;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.parse.StrUtils;

import java.util.List;

public class ListIntDBValue extends ListDBValue<Integer> {

    public ListIntDBValue(List<Integer> value) {
        super(value, DBValueType.LIST_INT);
    }

    @Override
    public Integer cast(String value) throws InvalidCommandException {
        return StrUtils.parseStringToInt(value);
    }
}
