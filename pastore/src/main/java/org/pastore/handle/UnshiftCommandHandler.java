package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.KeyNotExistException;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

import java.io.IOException;

public class UnshiftCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, ClientException {
        String key = command.getProperties().get(PropertyType.KEY);

        if (! store.keyExists(key)) {
            throw new KeyNotExistException(key);
        }
        DBValue dbValue = store.getDBValueByKey(key);
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        dbValue.unshift(plainValue);
        return new OkResponse();
    }
}
