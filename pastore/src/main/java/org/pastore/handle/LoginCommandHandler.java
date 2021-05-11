package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.auth.AuthWrongPasswordException;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

import java.io.IOException;

public class LoginCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, ClientException {
        String password = command.getProperties().get(PropertyType.KEY);
        String actualPassword = System.getenv("PASTORE_PASS");
        if (password.equals(actualPassword)) {
            connection.setLoggedIn();
            return new OkResponse();
        }
        throw new AuthWrongPasswordException();
    }
}
