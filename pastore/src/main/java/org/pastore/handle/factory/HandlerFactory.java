package org.pastore.handle.factory;

import org.pastore.command.CommandType;
import org.pastore.handle.*;

import java.util.HashMap;
import java.util.Map;


public class HandlerFactory implements IHandlerFactory {

    private static IHandlerFactory instance;

    private Map<CommandType, IHandle> handlers;

    private HandlerFactory() {
        this.handlers = new HashMap<>();
        this.handlers.put(CommandType.SET, new SetCommandHandler());
        this.handlers.put(CommandType.EXIT, new ExitCommandHandler());
        this.handlers.put(CommandType.DB, new DBCommandHandler());
        this.handlers.put(CommandType.USE, new UseCommandHandler());
        this.handlers.put(CommandType.EXISTS, new ExistsCommandHandler());
        this.handlers.put(CommandType.INCREMENT, new IncrementCommandHandler());
        this.handlers.put(CommandType.PUSH, new PushCommandHandler());
        this.handlers.put(CommandType.POP, new PopCommandHandler());
        this.handlers.put(CommandType.SHIFT, new ShiftCommandHandler());
        this.handlers.put(CommandType.UNSHIFT, new UnshiftCommandHandler());
        this.handlers.put(CommandType.SIZE, new SizeCommandHandler());
        this.handlers.put(CommandType.INDEX, new IndexCommandHandler());
        this.handlers.put(CommandType.GET, new GetCommandHandler());
        this.handlers.put(CommandType.DELETE, new DeleteCommandHandler());
        this.handlers.put(CommandType.LOGIN, new LoginCommandHandler());
        this.handlers.put(CommandType.EXPIRES, new ExpiresCommandHandler());
    }

    public static IHandlerFactory getInstance() {
        if (instance == null) {
            instance = new HandlerFactory();
        }
        return instance;
    }

    @Override
    public IHandle getHandler(CommandType commandType) {
        return this.handlers.get(commandType);
    }
}
