package org.pastore.command.exception;

public class UnknownOptionException extends InvalidCommandException {

    private static final String PREFIX = "Invalid option name";

    // suggestions put here
    private static final String MESSAGE = "%s is invalid option";

    public UnknownOptionException(String command) {
        super(PREFIX, String.format(MESSAGE, command));
    }
}
