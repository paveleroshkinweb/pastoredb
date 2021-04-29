package org.pastore.command.exception;

public class UnknownCommandException extends InvalidCommandException{

    private static final String PREFIX = "Invalid command name";

    // suggestions put here
    private static final String MESSAGE = "%s is invalid command";

    public UnknownCommandException(String command) {
        super(PREFIX, String.format(MESSAGE, command));
    }
}
