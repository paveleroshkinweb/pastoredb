package org.pastore.exception.client.command;

public class EmptyListException extends InvalidCommandException {

    private static final String MESSAGE = "List is empty!";

    private static final int CODE = 0;

    public EmptyListException(){
        super(MESSAGE, CODE);
    }
}
