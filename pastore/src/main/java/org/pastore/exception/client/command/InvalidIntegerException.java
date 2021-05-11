package org.pastore.exception.client.command;

public class InvalidIntegerException extends InvalidCommandException {

    private static final int CODE = 2;

    public InvalidIntegerException(String msg) {
        super(msg, CODE);
    }
}
