package org.pastore.exception.command;

public class RegisteredKeywordException extends InvalidCommandException {

    private static final String MESSAGE = "%s is not allowed as a key because it's a registered command!";

    public RegisteredKeywordException(String property) {
        super(String.format(MESSAGE, property));
    }
}
