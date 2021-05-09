package org.pastore.exception.client.command;

import org.pastore.command.CommandType;
import org.pastore.db.value.DBValueType;

public class NotAllowedCommandException extends InvalidCommandException {

    private static final String MESSAGE = "%s is not allowed for type %s";

    private static int CODE = 11;

    public NotAllowedCommandException(CommandType commandType, DBValueType dbValueType) {
        super(String.format(MESSAGE, commandType.getName(), dbValueType.getPrefix()), CODE);
    }
}
