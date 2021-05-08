package org.pastore.handle;

import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.command.Command;
import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;
import org.pastore.connection.Connection;
import org.pastore.db.Store;
import org.pastore.db.value.*;
import org.pastore.handle.helper.DataHelper;
import org.pastore.parse.StrUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SetCommandHandler implements IHandle {

    private static final String INVALID_OPTION = "%s is not allowed for existing key!";

    @Override
    public void handle(Command command, Connection connection, Store store) throws IOException, InvalidCommandException {
        String key = command.getProperties().get(PropertyType.KEY);
        if (store.keyExists(key)) {
            updateValue(key, command, connection, store);
        } else {
            createNewValue(key, command, connection, store);
        }
        connection.setOKResponse();
    }

    private void updateValue(String key, Command command, Connection connection, Store store) throws InvalidCommandException{
        if (command.getOptions().get(OptionType.EXPIRES) != null) {
            throw new InvalidCommandException(String.format(INVALID_OPTION, OptionType.EXPIRES.getName()));
        }
        if (command.getOptions().get(OptionType.TYPE) != null) {
            throw new InvalidCommandException(String.format(INVALID_OPTION, OptionType.TYPE.getName()));
        }
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        DBValue dbValue = store.getDBValueByKey(key);
        DBValueType type = dbValue.getDbValueType();
        if (type == DBValueType.INTEGER) {
            Integer value = StrUtils.parseStringToInt(plainValue);
            dbValue.setValue(value);
        } else if (type == DBValueType.STRING) {
            dbValue.setValue(plainValue);
        } else if (type == DBValueType.LIST_INT) {
            List<IntegerDBValue> intList = intListToDBList(StrUtils.parseStringToIntList(plainValue));
            dbValue.setValue(intList);
        } else {
            List<StringDBValue> strList = strListToDBList(StrUtils.parseStringToStrList(plainValue));
            dbValue.setValue(strList);
        }
    }

    private void createNewValue(String key, Command command, Connection connection, Store store) throws InvalidCommandException, IOException{
        String plainDataType = command.getOptions().get(OptionType.TYPE);
        DBValueType dbValueType = DBValueType.getTypeByName(plainDataType, DBValueType.STRING);
        String plainValue = command.getProperties().get(PropertyType.VALUE);
        Integer expires = DataHelper.transformExpires(command.getOptions().get(OptionType.EXPIRES));

        DBValue value;

        if (dbValueType == DBValueType.INTEGER) {
            value = createNewIntegerValue(plainValue);
        } else if (dbValueType == DBValueType.STRING) {
            value = createNewStringValue(plainValue);
        } else if (dbValueType == DBValueType.LIST_INT) {
            value = createNewIntListValue(plainValue);
        } else {
            value = createNewStrListValue(plainValue);
        }
        store.addDBValue(key, value, expires);
    }

    private DBValue createNewIntegerValue(String plainValue) throws InvalidCommandException {
        Integer value = StrUtils.parseStringToInt(plainValue);
        DBValue dbValue = new IntegerDBValue(value);
        return dbValue;
    }

    private DBValue createNewStringValue(String plainValue) throws InvalidCommandException {
        DBValue dbValue = new StringDBValue(plainValue);
        return dbValue;
    }

    private DBValue createNewIntListValue(String plainValue) throws InvalidCommandException {
        List<IntegerDBValue> intList = intListToDBList(StrUtils.parseStringToIntList(plainValue));
        DBValue value = new ListIntDBValue(intList);
        return value;
    }

    private DBValue createNewStrListValue(String plainValue) throws InvalidCommandException{
        List<StringDBValue> strList = strListToDBList(StrUtils.parseStringToStrList(plainValue));
        DBValue value = new ListStrDBValue(strList);
        return value;
    }

    private List<IntegerDBValue> intListToDBList(List<Integer> list) {
        return list.stream().map(IntegerDBValue::new).collect(Collectors.toList());
    }

    private List<StringDBValue> strListToDBList(List<String> list) {
        return list.stream().map(StringDBValue::new).collect(Collectors.toList());
    }
}
