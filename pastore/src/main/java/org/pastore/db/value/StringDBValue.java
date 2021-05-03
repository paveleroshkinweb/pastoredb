package org.pastore.db.value;

public class StringDBValue extends DBValue<String> {

    public StringDBValue(String value) {
        super(value, DBValueType.STRING);
    }

}
