package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

public class PushCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws InvalidCommandException {
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        dbValue.push(plainValue);
        return new OkResponse();
    }
}
