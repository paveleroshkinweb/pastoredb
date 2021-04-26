package org.pastore.db;

public class StringDBValue extends DBValue<String>{

    public StringDBValue(String value) {
        super(value, DBValueType.STRING);
    }
}
