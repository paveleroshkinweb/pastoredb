package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandHandler {

    private static final Map<CommandType, CommandHandler> handlers = new HashMap<>();

    public abstract void handle(Command command, Connection connection, Store store);

    public static CommandHandler getHandlerByCommand(CommandType commandType) {
        if (handlers.get(commandType) != null) {
            return handlers.get(commandType);
        }
        CommandHandler handler = null;
        if (commandType == CommandType.SET) {
            handler = new SetCommandHandler();
        }
        handlers.put(commandType, handler);
        return handler;
    }
}
