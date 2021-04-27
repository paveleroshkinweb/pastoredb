package org.pastore.handle;

import org.pastore.command.Command;
import org.pastore.command.exception.InvalidCommand;
import org.pastore.command.option.OptionType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.DBValue;
import org.pastore.db.value.DBValueType;
import org.pastore.db.value.IntegerDBValue;
import org.pastore.db.value.StringDBValue;

import java.io.IOException;
import java.util.Map;

public class SetCommandHandler implements IHandle {

    private static final String ALREADY_EXISTS = "Property: %s already exists! Please use UPDATE to change value!";

    private static final String INVALID_INT = "Invalid integer format: %s";

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommand {
        Map<OptionType, String> options = command.getOptions();
        String property = command.getProperty();
        if (store.keyExists(property)) {
            connection.setErrorResponse(String.format(ALREADY_EXISTS, property));
            return;
        }

        DBValue value = null;
        String type = options.get(OptionType.TYPE);
        String plainValue = options.get(OptionType.VALUE);

        if (type.equals(DBValueType.STRING.getPrefix())) {
            value = new StringDBValue(plainValue);

        } else if(type.equals(DBValueType.INTEGER.getPrefix())) {
            try {
                value = new IntegerDBValue(Integer.valueOf(plainValue));
            } catch (NumberFormatException e) {
                throw new InvalidCommand(String.format(INVALID_INT, plainValue));
            }

        } else if(type.equals(DBValueType.LIST_INT.getPrefix())) {
            //
        }

        store.addDBValue(property, value);
        connection.setOKResponse();
    }
}
