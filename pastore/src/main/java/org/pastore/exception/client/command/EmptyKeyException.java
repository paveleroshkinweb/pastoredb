package org.pastore.exception.client.command;

public class EmptyKeyException extends InvalidCommandException {

    private static final String DEFAULT_MESSAGE = "Empty %s for command %s is not allowed";

    private static final int CODE = 0;

    public EmptyKeyException() {
        super(String.format(DEFAULT_MESSAGE, "key", "this"), CODE);
    }

    public EmptyKeyException(String key) {
        super(String.format(DEFAULT_MESSAGE, key, "this"), CODE);
    }

    public EmptyKeyException(String key, String command) {
        super(String.format(DEFAULT_MESSAGE, key, command), CODE);
    }
}
