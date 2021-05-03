package org.pastore.clientexception.command;

import org.pastore.clientexception.BaseException;

public class InvalidCommandException extends BaseException {

    private static final int CODE = 3;

    private static final String ERROR_PREFIX = "Invalid command error";

    public InvalidCommandException(String msg) {
        super(ERROR_PREFIX, CODE, msg);
    }

}
