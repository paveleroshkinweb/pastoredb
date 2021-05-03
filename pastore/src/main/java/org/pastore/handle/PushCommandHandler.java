package org.pastore.handle;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;
import org.pastore.parse.StrUtils;

import java.io.IOException;
import java.util.List;

public class PushCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String key = command.getProperties().get(PropertyType.KEY);

        if (! store.keyExists(key)) {
            throw new InvalidCommandException("key " + key + " is not existed!");
        }
        DBValue dbValue = store.getDBValueKey(key);
        if (dbValue.getDbValueType() != DBValueType.LIST_INT && dbValue.getDbValueType() != DBValueType.LIST_STR) {
            throw new InvalidCommandException(key + " is not a list!");
        }
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        if (dbValue.getDbValueType() == DBValueType.LIST_INT) {
            Integer value = StrUtils.parseStringToInt(plainValue);
            ((List<Integer>) dbValue.getValue()).add(value);
        } else {
            ((List<String>) dbValue.getValue()).add(plainValue);
        }
        connection.setOKResponse();
    }
}
