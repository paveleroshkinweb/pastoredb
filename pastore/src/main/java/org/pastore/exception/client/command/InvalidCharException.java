package org.pastore.exception.client.command;

public class InvalidCharException extends InvalidCommandException {

    private static final String MESSAGE = "Character %c is not allowed in key or property name!";

    private static final int CODE = 2;

    public InvalidCharException(char symbol) {
        super(String.format(MESSAGE, symbol), CODE);
    }
}
