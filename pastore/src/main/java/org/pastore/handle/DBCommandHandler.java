package org.pastore.handle;

import org.pastore.exception.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

import java.io.IOException;

public class DBCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String response = String.valueOf(store.getNumber());
        connection.setSuccessResponse(response);
    }
}
