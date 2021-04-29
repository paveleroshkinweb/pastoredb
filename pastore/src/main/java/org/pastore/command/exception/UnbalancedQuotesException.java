package org.pastore.command.exception;

public class UnbalancedQuotesException extends InvalidCommandException {

    private static final String MESSAGE = "unbalanced quotes in request";

    public UnbalancedQuotesException() {
        super(MESSAGE);
    }

}
