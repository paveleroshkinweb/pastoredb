package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.response.Response;
import org.pastore.response.SuccessResponse;

public class PopCommandHandler extends KeyRequiredCommandHandler {

    @Override
    public Response process(DBValue dbValue, Command command, Connection connection, Store store) throws InvalidCommandException {
        String result = dbValue.pop();
        return new SuccessResponse(result);
    }
}
