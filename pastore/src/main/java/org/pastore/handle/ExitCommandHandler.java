package org.pastore.handle;

import org.pastore.exception.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

import java.io.IOException;

public class ExitCommandHandler implements IHandle {

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        connection.closeConnection();
    }
}
