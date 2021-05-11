package org.pastore.exception.client.format;

public class UnbalancedQuotesException extends CommandFormatException {

    private static final int CODE = 2;

    public UnbalancedQuotesException(String msg) {
        super(msg, CODE);
    }

}
