package org.pastore.exception.client.command;

public class ArrayFormatException extends InvalidCommandException {

    private static final String MESSAGE = "Array must be wrapped with []";

    private static final int CODE = 16;

    public ArrayFormatException() {
        super(MESSAGE, CODE);
    }

}
