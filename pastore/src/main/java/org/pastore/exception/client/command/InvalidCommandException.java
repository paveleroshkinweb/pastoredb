package org.pastore.exception.client.command;

import org.pastore.exception.client.ClientException;

public class InvalidCommandException extends ClientException {

    private static final int GROUP_CODE = 3;

    private static final String ERROR_PREFIX = "Invalid command error";

    public InvalidCommandException(String msg, int code) {
        super(ERROR_PREFIX, GROUP_CODE, code, msg);
    }

}
