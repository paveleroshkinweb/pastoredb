package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.parse.StrUtils;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

public class IndexCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws ClientException {
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        String value = dbValue.index(StrUtils.parseStringToInt(plainValue));
        return new SuccessResponse(value);
    }
}
