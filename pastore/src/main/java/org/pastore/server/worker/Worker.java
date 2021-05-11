package org.pastore.server.worker;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.IDatabase;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.handle.IHandle;
import org.pastore.handle.factory.IHandlerFactory;
import org.pastore.parse.CommandParser;
import org.pastore.response.FailResponse;
import org.pastore.response.Response;
import org.pastore.server.middleware.Middleware;

import java.io.IOException;

public class Worker extends AbstractWorker {

    private static final Logger logger = Logger.getLogger(Worker.class);

    public Worker(IDatabase database, Connection connection, String plainCommand, IHandlerFactory handlerFactory, Middleware middleware) {
        super(database, connection, plainCommand, handlerFactory, middleware);
    }

    @Override
    public void run() {
        try {
            try {
                Command command = CommandParser.parseTextToCommand(this.getPlainCommand());
                boolean chainResult = this.getMiddleware().process(this.getConnection(), command);
                if (chainResult) {
                    int connectionDB = this.getConnection().getCurrentDB();
                    Store store = this.getDatabase().getStoreByIndex(connectionDB);

                    IHandle commandHandler = this.getHandlerFactory().getHandler(command.getCommandType());
                    Response response = commandHandler.handle(command, this.getConnection(), store);
                    if (response != null) {
                        this.getConnection().setResponse(response);
                    }
                }
            } catch (ClientException e) {
                Response response = new FailResponse(e.getMessage());
                this.getConnection().setResponse(response);
            }
        } catch (IOException e) {
            try {
                this.getConnection().closeConnection();
            } catch (IOException ioException) {
                logger.error(ioException);
            }
        }
    }

}
