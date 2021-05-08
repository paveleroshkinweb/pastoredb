package org.pastore.exception.client.command;

import org.pastore.exception.client.ClientException;

public class InvalidCommandException extends ClientException {

    private static final int CODE = 0;

    private static final String ERROR_PREFIX = "Invalid command error";

    public InvalidCommandException(String msg) {
        super(ERROR_PREFIX, CODE, msg);
    }

}
