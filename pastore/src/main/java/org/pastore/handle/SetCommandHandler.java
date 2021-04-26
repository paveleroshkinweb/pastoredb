package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;

public class SetCommandHandler extends CommandHandler {

    public SetCommandHandler() {
        super();
    }

    @Override
    public void handle(Command command, Connection connection, Store store) {
        int connectionDB = connection.getCurrentDB();

    }
}
