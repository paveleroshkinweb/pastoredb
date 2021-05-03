package org.pastore.handle;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;

import java.io.IOException;

public class IncrementCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String key = command.getProperties().get(PropertyType.KEY);
        if (! store.keyExists(key)) {
            throw new InvalidCommandException("key " + key + " is not existed!");
        }
        DBValue dbValue = store.getDBValueKey(key);
        if (dbValue.getDbValueType() != DBValueType.INTEGER) {
            throw new InvalidCommandException("key " + key + " is not an integer!");
        }
        Integer newValue = ((Integer) dbValue.getValue()) + 1;
        dbValue.setValue(newValue);
        connection.setOKResponse();
    }
}
