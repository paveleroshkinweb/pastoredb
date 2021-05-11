package org.pastore.exception.client.unknown;

public class UnknownOptionException extends UnknownFieldException {

    private static final String MESSAGE = "%s is invalid option";

    private static final int CODE = 1;

    public UnknownOptionException(String command) {
        super(String.format(MESSAGE, command), CODE);
    }
}
