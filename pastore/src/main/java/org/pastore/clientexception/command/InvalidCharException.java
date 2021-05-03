package org.pastore.clientexception.command;

public class InvalidCharException extends InvalidCommandException {

    private static final String MESSAGE = "Character %c is not allowed in key or property name!";

    public InvalidCharException(char symbol) {
        super(String.format(MESSAGE, symbol));
    }
}
