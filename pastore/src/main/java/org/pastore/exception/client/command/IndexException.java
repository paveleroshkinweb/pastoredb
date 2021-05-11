package org.pastore.exception.client.command;

public class IndexException extends InvalidCommandException {

    private static final String MESSAGE = "%i is bigger then collection size";

    private static final int CODE = 1;

    public IndexException() {
        super(MESSAGE, CODE);
    }
}
