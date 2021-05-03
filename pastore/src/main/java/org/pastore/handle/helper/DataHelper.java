package org.pastore.handle.helper;

import org.pastore.exception.command.InvalidCommandException;

public class DataHelper {

    public static Integer transformExpires(String expires) throws InvalidCommandException {
        if (expires == null) {
            return null;
        }
        try {
            Integer intExpires = Integer.valueOf(expires);
            return intExpires;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(expires + " is not a valid integer!");
        }
    }
}
