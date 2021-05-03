package org.pastore.clientexception.command;

public class UnknownCommandException extends InvalidCommandException{

    private static final String MESSAGE = "%s is invalid command";

    public UnknownCommandException(String command) {
        super(String.format(MESSAGE, command));
    }
}
