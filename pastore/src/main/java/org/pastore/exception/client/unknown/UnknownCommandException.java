package org.pastore.exception.client.unknown;

public class UnknownCommandException extends UnknownFieldException {

    private static final String MESSAGE = "%s is invalid command";

    private static final int CODE = 0;

    public UnknownCommandException(String command) {
        super(String.format(MESSAGE, command), CODE);
    }
}
