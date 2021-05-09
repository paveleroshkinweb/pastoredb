package org.pastore.exception.client.command;

public class EscapeException extends InvalidCommandException {

    private static final int CODE = 15;

    public EscapeException(String msg) {
        super(msg, CODE);
    }
}
