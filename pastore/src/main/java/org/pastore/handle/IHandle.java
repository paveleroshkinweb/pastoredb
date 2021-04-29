package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.exception.InvalidCommandException;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

import java.io.IOException;

public interface IHandle {

    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException;


}
