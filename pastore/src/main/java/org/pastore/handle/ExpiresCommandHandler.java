package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.handle.helper.DataHelper;
import org.pastore.response.OkResponse;
import org.pastore.response.Response;

public class ExpiresCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws ClientException {
        String key = command.getProperties().get(PropertyType.KEY);
        Integer expires = DataHelper.transformExpires(command.getProperties().get(PropertyType.VALUE));
        store.setExpires(key, expires);
        return new OkResponse();
    }
}
