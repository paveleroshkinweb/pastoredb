package org.pastore.server;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.InvalidCommand;
import org.pastore.connection.Connection;
import org.pastore.db.Database;
import org.pastore.db.Store;
import org.pastore.handle.HandlerFactory;
import org.pastore.handle.IHandle;
import org.pastore.parse.CommandParser;

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
                if (! connection.isLoggedIn() && command.getCommandType() != CommandType.LOGIN) {
                    connection.setErrorResponse("Pastore has been started in password-protected mode, please login!");
                    return;
                }

                int connectionDB = connection.getCurrentDB();
                Store store = Database.getInstance().getStoreByIndex(connectionDB);

                IHandle commandHandler = HandlerFactory.getHandlerByCommand(command.getCommandType());
                commandHandler.handle(command, connection, store);
            } catch (InvalidCommand e) {
                this.connection.setErrorResponse(e.getMessage());
            }
        } catch (IOException e) {
            try {
                connection.closeConnection();
            } catch (IOException ioException) {
                logger.error("IOException occurred during closing connection", ioException);
            }
        }
    }
}
