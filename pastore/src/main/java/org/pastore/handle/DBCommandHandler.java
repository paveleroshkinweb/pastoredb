package org.pastore.handle;

import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

import java.io.IOException;

public class DBCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String response = String.valueOf(store.getNumber());
        return new SuccessResponse(response);
    }
}
