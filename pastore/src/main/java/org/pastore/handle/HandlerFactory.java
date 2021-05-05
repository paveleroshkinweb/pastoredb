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
        } else if (commandType == CommandType.POP) {
            handler = new PopCommandHandler();
        } else if (commandType == CommandType.SHIFT) {
            handler = new ShiftCommandHandler();
        } else if (commandType == CommandType.UNSHIFT) {
            handler = new UnshiftCommandHandler();
        } else if (commandType == CommandType.SIZE) {
            handler = new SizeCommandHandler();
        } else if (commandType == CommandType.INDEX) {
            handler = new IndexCommandHandler();
        } else if (commandType == CommandType.GET) {
            handler = new GetCommandHandler();
        } else if (commandType == CommandType.DELETE) {
            handler = new DeleteCommandHandler();
        }
        handlers.put(commandType, handler);
        return handler;
    }
}
