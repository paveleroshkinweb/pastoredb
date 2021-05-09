package org.pastore.exception.client.command;

public class UnbalancedQuotesException extends InvalidCommandException {

    private static final int CODE = 5;

    public UnbalancedQuotesException(String msg) {
        super(msg, CODE);
    }

}
