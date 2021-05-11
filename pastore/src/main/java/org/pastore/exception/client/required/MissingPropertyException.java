package org.pastore.exception.client.required;

public class MissingPropertyException extends EmptyRequiredFieldException {

    private static final String MESSAGE = "%s can't be empty!";

    private static final int CODE = 3;

    public MissingPropertyException(String property) {
        super(String.format(MESSAGE, property), CODE);
    }
}
