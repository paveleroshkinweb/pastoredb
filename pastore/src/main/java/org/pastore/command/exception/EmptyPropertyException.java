package org.pastore.command.exception;

public class EmptyPropertyException extends InvalidCommandException {

    private static final String MESSAGE = "Empty key for this command is not allowed!";

    public EmptyPropertyException() {
        super(MESSAGE);
    }
}
