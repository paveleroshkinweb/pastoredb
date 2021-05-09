package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

public class DeleteCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws InvalidCommandException {
        String key = command.getProperties().get(PropertyType.KEY);
        store.removeValueByKey(key);
        return new OkResponse();
    }
}
