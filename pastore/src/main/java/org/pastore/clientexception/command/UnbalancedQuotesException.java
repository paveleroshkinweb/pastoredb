package org.pastore.clientexception.command;

public class UnbalancedQuotesException extends InvalidCommandException {

    public UnbalancedQuotesException(String msg) {
        super(msg);
    }

}
