package org.pastore.exception.client.command;

public class InvalidOptionFormatException extends InvalidCommandException {

    private static final int CODE = 14;

    public InvalidOptionFormatException(String msg) {
        super(msg, CODE);
    }
}
