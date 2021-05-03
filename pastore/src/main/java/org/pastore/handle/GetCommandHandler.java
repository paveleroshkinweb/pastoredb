package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.command.InvalidCommandException;

import java.io.IOException;

public class GetCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String key = command.getProperties().get(PropertyType.KEY);

        if (! store.keyExists(key)) {
            throw new InvalidCommandException("key " + key + " does not exist!");
        }
        DBValue dbValue = store.getDBValueKey(key);
        String response = dbValue.toResponse();
        connection.setSuccessResponse(response);
    }
}
