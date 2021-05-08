package org.pastore.exception.client.command;

public class UnknownOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s is invalid option";

    public UnknownOptionException(String command) {
        super(String.format(MESSAGE, command));
    }
}
