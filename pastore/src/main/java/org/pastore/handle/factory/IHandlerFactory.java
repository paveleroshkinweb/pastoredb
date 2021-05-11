package org.pastore.handle.factory;

import org.pastore.command.CommandType;
import org.pastore.handle.IHandle;

public interface IHandlerFactory {

    IHandle getHandler(CommandType commandType);
}
