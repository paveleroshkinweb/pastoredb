package org.pastore.exception.command;

import org.pastore.exception.BaseException;

public class InvalidCommandException extends BaseException {

    private static final int CODE = 3;

    private static final String ERROR_PREFIX = "Invalid command error";

    public InvalidCommandException(String msg) {
        super(ERROR_PREFIX, CODE, msg);
    }

}
