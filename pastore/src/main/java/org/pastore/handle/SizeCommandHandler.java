package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.store.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.ClientException;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

public class SizeCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws ClientException {
        String size = dbValue.size();
        return new SuccessResponse(size);
    }
}
