package org.pastore.server;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.connection.Connection;
import org.pastore.db.Database;
import org.pastore.db.Store;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.handle.HandlerFactory;
import org.pastore.handle.IHandle;
import org.pastore.parse.CommandParser;
import org.pastore.response.FailResponse;
import org.pastore.response.Response;
import org.pastore.response.ResponseType;

import java.io.IOException;

public class Worker implements Runnable {

    private static final Logger logger = Logger.getLogger(Worker.class);

    private Connection connection;

    private String plainCommand;

    public Worker(Connection connection, String plainCommand) {
        this.connection = connection;
        this.plainCommand = plainCommand;
    }

    @Override
    public void run() {
        try {
            try {
                Command command = CommandParser.parseTextToCommand(plainCommand);
                // вынести в AUTH middleware
                if (! connection.isLoggedIn() && command.getCommandType() != CommandType.LOGIN) {
                    connection.setErrorResponse("Pastore has been started in password-protected mode, please login!");
                    return;
                }

                int connectionDB = connection.getCurrentDB();
                Store store = Database.getInstance().getStoreByIndex(connectionDB);

                IHandle commandHandler = HandlerFactory.getHandlerByCommand(command.getCommandType());
                Response response = commandHandler.handle(command, connection, store);
                sendResponse(response);
            } catch (InvalidCommandException e) {
                Response response = new FailResponse(e.getMessage());
                sendResponse(response);
            }
        } catch (IOException e) {
            try {
                connection.closeConnection();
            } catch (IOException ioException) {
                logger.error(ioException);
            }
        }
    }

    // переделать на цепочку обязанностей
    public void sendResponse(Response response) throws IOException {
        if (response == null) {
            return;
        }
        if (response.getResponseType() == ResponseType.OK) {
            this.connection.setOKResponse();
        } else if (response.getResponseType() == ResponseType.FAIL) {
            this.connection.setErrorResponse(response.getResponse());
        } else if (response.getResponseType() == ResponseType.SUCCESS) {
            this.connection.setSuccessResponse(response.getResponse());
        }
    }
}
