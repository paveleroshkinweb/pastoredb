package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.parse.StrUtils;

public class IndexCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public String process(DBValue dbValue, Command command, Connection connection, Store store) throws InvalidCommandException {
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        String value = dbValue.index(StrUtils.parseStringToInt(plainValue));
        return value;
    }
}
