package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.response.Response;

import java.io.IOException;

public interface IHandle {

    Response handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException;


}
