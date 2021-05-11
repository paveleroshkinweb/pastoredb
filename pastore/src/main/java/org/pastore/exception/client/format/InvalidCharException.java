package org.pastore.exception.client.format;

public class InvalidCharException extends CommandFormatException {

    private static final String MESSAGE = "Character %c is not allowed in key or property name!";

    private static final int CODE = 3;

    public InvalidCharException(char symbol) {
        super(String.format(MESSAGE, symbol), CODE);
    }
}
