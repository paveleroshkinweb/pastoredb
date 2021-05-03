package org.pastore.clientexception.command;

public class EmptyKeyException extends InvalidCommandException {

    private static final String MESSAGE = "Empty key for this command is not allowed!";

    public EmptyKeyException() {
        super(MESSAGE);
    }
}
