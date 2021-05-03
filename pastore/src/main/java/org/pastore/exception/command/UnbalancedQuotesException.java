package org.pastore.exception.command;

public class UnbalancedQuotesException extends InvalidCommandException {

    public UnbalancedQuotesException(String msg) {
        super(msg);
    }

}
