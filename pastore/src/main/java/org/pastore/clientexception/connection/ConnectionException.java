package org.pastore.clientexception.connection;

import org.pastore.clientexception.BaseException;

public class ConnectionException extends BaseException {

    private static final int CODE = 1;

    private static final String ERROR_PREFIX = "Client error";

    public ConnectionException(String msg) {
        super(ERROR_PREFIX, CODE, msg);
    }
}
