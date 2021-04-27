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
        }
        handlers.put(commandType, handler);
        return handler;
    }
}
