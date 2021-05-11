package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.db.value.NullDBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

import java.io.IOException;

public class GetCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, ClientException {
        String key = command.getProperties().get(PropertyType.KEY);
        if (! store.keyExists(key)) {
            return new SuccessResponse(NullDBValue.getInstance().toResponse());
        }
        DBValue dbValue = store.getDBValueByKey(key);
        return new SuccessResponse(dbValue.toResponse());
    }
}
