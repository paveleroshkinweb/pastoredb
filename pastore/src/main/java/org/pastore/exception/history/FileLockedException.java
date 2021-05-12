package org.pastore.exception.history;

public class FileLockedException extends HistoryException {

    public FileLockedException(String msg) {
        super(msg);
    }

    public FileLockedException(String msg, Throwable t) {
        super(msg, t);
    }
}
