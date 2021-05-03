package org.pastore.handle;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

import java.io.IOException;

public class ExistsCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String keyToCheck = command.getProperties().get(PropertyType.KEY);
        String isKeyExists = store.keyExists(keyToCheck) ? "1" : "0";
        connection.setSuccessResponse(isKeyExists);
    }
}
