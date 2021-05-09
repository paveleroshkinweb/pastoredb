package org.pastore.handle;

import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

import java.io.IOException;

public class ExistsCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String keyToCheck = command.getProperties().get(PropertyType.KEY);
        String isKeyExists = store.keyExists(keyToCheck) ? "1" : "0";
        return new SuccessResponse(isKeyExists);
    }
}
