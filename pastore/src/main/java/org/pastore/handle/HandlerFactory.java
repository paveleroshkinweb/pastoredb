package org.pastore.handle;

import org.pastore.command.CommandType;

import java.util.HashMap;
import java.util.Map;

public abstract class HandlerFactory {

    private static final Map<CommandType, IHandle> handlers = new HashMap<>();

    public static IHandle getHandlerByCommand(CommandType commandType) {
        if (handlers.get(commandType) != null) {
            return handlers.get(commandType);
        }
        IHandle handler = null;
        if (commandType == CommandType.SET) {
            handler = new SetCommandHandler();
        } else if (commandType == CommandType.EXIT) {
            handler = new ExitCommandHandler();
        } else if (commandType == CommandType.DB) {
            handler = new DBCommandHandler();
        } else if (commandType == CommandType.USE) {
            handler = new UseCommandHandler();
        } else if (commandType == CommandType.EXISTS) {
            handler = new ExistsCommandHandler();
        } else if (commandType == CommandType.INCREMENT) {
            handler = new IncrementCommandHandler();
        } else if (commandType == CommandType.PUSH) {
            handler = new PushCommandHandler();
        }
        handlers.put(commandType, handler);
        return handler;
    }
}
