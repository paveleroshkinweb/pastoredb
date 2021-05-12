package org.pastore.exception.history;

public class HistoryException extends RuntimeException {

    public HistoryException(String msg, Throwable t) {
        super(msg, t);
    }

    public HistoryException(String msg) {
        super(msg);
    }
}
