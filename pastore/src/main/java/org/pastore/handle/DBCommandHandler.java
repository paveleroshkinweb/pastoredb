package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

import java.io.IOException;

public class DBCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, ClientException {
        String response = String.valueOf(store.getNumber());
        return new SuccessResponse(response);
    }
}
