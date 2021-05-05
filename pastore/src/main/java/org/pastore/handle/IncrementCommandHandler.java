package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.command.InvalidCommandException;

public class IncrementCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public String process(DBValue dbValue, Command command, Connection connection, Store store) throws InvalidCommandException {
        dbValue.increment();
        return null;
    }
}
