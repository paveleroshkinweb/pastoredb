package org.pastore.exception.client.command;

public class UnknownOptionException extends InvalidCommandException {

    private static final String MESSAGE = "%s is invalid option";

    private static final int CODE = 7;

    public UnknownOptionException(String command) {
        super(String.format(MESSAGE, command), CODE);
    }
}
