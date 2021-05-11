package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.MaxDatabaseException;
import org.pastore.parse.StrUtils;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

import java.io.IOException;

public class UseCommandHandler implements IHandle {

    @Override
    public Response handle(Command command, Connection connection, Store store) throws IOException, ClientException {
        Integer dbNumber = StrUtils.parseStringToInt(command.getProperties().get(PropertyType.KEY));
        int storesNumber = store.getDatabase().getStoresNumber();
        if (dbNumber > storesNumber - 1) {
            throw new MaxDatabaseException(storesNumber - 1);
        }
        connection.setCurrentDB(dbNumber);
        return new OkResponse();
    }
}
