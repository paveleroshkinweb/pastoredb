package org.pastore.handle.helper;

import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.InvalidIntegerException;

public class DataHelper {

    public static Integer transformExpires(String expires) throws ClientException {
        if (expires == null) {
            return null;
        }
        try {
            Integer intExpires = Integer.valueOf(expires);
            return intExpires;
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(expires + " is not a valid integer!");
        }
    }
}
