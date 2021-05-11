package org.pastore.exception.client.format;

public class EscapeException extends CommandFormatException {

    private static final int CODE = 1;

    public EscapeException(String msg) {
        super(msg, CODE);
    }
}
