package org.pastore.exception.command;

public class UnknownOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s is invalid option";

    public UnknownOptionException(String command) {
        super(String.format(MESSAGE, command));
    }
}
