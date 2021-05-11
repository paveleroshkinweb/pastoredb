package org.pastore.exception.client.command;

public class KeyNotExistException extends InvalidCommandException {

    private static final String MESSAGE = "Key %s does not exist!";

    private static final int CODE = 5;

    public KeyNotExistException(String key) {
        super(String.format(MESSAGE, key), CODE);
    }
}
