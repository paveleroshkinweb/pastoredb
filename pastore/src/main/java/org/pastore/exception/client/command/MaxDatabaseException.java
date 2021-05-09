package org.pastore.exception.client.command;

public class MaxDatabaseException extends InvalidCommandException {

    private static final String MESSAGE = "The max database number is %i";

    private static final int CODE = 9;

    public MaxDatabaseException(int number) {
        super(String.format(MESSAGE, number), CODE);
    }
}
