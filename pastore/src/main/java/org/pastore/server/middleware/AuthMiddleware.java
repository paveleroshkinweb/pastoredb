package org.pastore.server.middleware;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.auth.AuthRequiredException;

import java.io.IOException;

public class AuthMiddleware extends Middleware {

    @Override
    public boolean process(Connection connection, Command command) throws IOException, ClientException {
        if (! connection.isLoggedIn() && command.getCommandType().isLoginRequired()) {
            throw new AuthRequiredException();
        }
        command.setStoreNumber(connection.getCurrentDB());
        return true;
    }
}
