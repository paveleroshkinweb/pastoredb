package org.pastore.clientexception.server;

import org.pastore.clientexception.BaseException;

public class ServerException extends BaseException {

    private static final int CODE = 0;

    private static final String ERROR_PREFIX = "Server error";

    public ServerException(String errMessage) {
        super(ERROR_PREFIX, CODE, errMessage);
    }

}
