package org.pastore.exception.client.format;

import org.pastore.exception.client.ClientException;

public class CommandFormatException extends ClientException {

    private static final int GROUP_CODE = 0;

    private static final String ERROR_MSG = "Invalid command format";

    public CommandFormatException(String description, int code) {
        super(ERROR_MSG, GROUP_CODE, code, description);
    }
}
