package org.pastore.clientexception;

public class BaseException extends Exception {

    public BaseException(final String prefix, final int code, final String msg) {
        super(prefix + ":" + code + ":" + msg);
    }

}
