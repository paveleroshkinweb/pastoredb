package org.pastore.handle;

import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.Database;
import org.pastore.db.Store;
import org.pastore.exception.client.command.MaxDatabaseException;
import org.pastore.parse.StrUtils;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

import java.io.IOException;

public class UseCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        Integer dbNumber = StrUtils.parseStringToInt(command.getProperties().get(PropertyType.KEY));
        int storesNumber = Database.getInstance().getStoresNumber();
        if (dbNumber > storesNumber - 1) {
            throw new MaxDatabaseException(storesNumber - 1);
        }
        connection.setCurrentDB(dbNumber);
        return new OkResponse();
    }
}
