package org.pastore.exception.client.command;

public class MissingPropertyException extends InvalidCommandException {

    private static final String MESSAGE = "%s can't be empty!";

    private static final int CODE = 8;

    public MissingPropertyException(String property) {
        super(String.format(MESSAGE, property), CODE);
    }
}
