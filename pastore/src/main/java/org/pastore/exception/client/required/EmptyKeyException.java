package org.pastore.exception.client.required;

public class EmptyKeyException extends EmptyRequiredFieldException {

    private static final String DEFAULT_MESSAGE = "Empty %s for command %s is not allowed";

    private static final int CODE = 0;

    public EmptyKeyException() {
        super(String.format(DEFAULT_MESSAGE, "key", "this"), CODE);
    }

    public EmptyKeyException(String key) {
        super(String.format(DEFAULT_MESSAGE, key, "this"), CODE);
    }

}
