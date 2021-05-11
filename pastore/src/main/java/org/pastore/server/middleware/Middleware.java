package org.pastore.server.middleware;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.exception.client.ClientException;

import java.io.IOException;

public abstract class Middleware {

    private static final Logger logger = Logger.getLogger(Middleware.class);

    private Middleware next;

    public Middleware setNext(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean process(Connection connection, Command command) throws IOException, ClientException;

    public boolean processAndGoNext(Connection connection, Command command) throws ClientException {
        try {
            boolean goNext = this.process(connection, command);
            if (goNext && next != null) {
                return next.processAndGoNext(connection, command);
            }
            return goNext;
        } catch (IOException e) {
            try {
                connection.closeConnection();
            } catch (IOException ioException) {
                logger.error(ioException);
            }
        }
        return false;
    }

}
