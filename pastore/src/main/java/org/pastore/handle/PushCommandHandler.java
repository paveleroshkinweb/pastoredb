package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

public class PushCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws ClientException {
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        dbValue.push(plainValue);
        return new OkResponse();
    }
}
