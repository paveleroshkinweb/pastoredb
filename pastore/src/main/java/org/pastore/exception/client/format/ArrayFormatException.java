package org.pastore.exception.client.format;

public class ArrayFormatException extends CommandFormatException {

    private static final String MESSAGE = "Array must be wrapped with []";

    private static final int CODE = 0;

    public ArrayFormatException() {
        super(MESSAGE, CODE);
    }

}
