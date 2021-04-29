package org.pastore.command.exception;

public class InvalidCharInKeyException extends InvalidCommandException {

    private static final String PREFIX = "Invalid key format";

    private static final String MESSAGE = "Character %c is not allowed in key!";

    public InvalidCharInKeyException(char symbol) {
        super(PREFIX, String.format(MESSAGE, symbol));
    }
}
