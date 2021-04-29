package org.pastore.command.exception;

public class InvalidCommandException extends Exception {

    private static final String DEFAULT_ERROR_PREFIX = "Invalid command format";

    public InvalidCommandException(String prefix, String msg) {
        super(prefix + ": " + msg);
    }

    public InvalidCommandException(String msg) {
        super(DEFAULT_ERROR_PREFIX + ": " + msg);
    }

}
