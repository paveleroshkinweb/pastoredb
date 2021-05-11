package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.response.Response;

import java.io.IOException;

public interface IHandle {

    Response handle(Command command, Connection connection, Store store) throws IOException, ClientException;


}
