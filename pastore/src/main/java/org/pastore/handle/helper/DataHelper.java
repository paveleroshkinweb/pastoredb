package org.pastore.handle.helper;

import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.exception.client.command.InvalidOptionFormatException;

public class DataHelper {

    public static Integer transformExpires(String expires) throws InvalidCommandException {
        if (expires == null) {
            return null;
        }
        try {
            Integer intExpires = Integer.valueOf(expires);
            return intExpires;
        } catch (NumberFormatException e) {
            throw new InvalidOptionFormatException(expires + " is not a valid integer!");
        }
    }
}
