package org.pastore.db.value;

public class NullDBValue extends DBValue<NullValue> {

    private static NullDBValue instance;

    private NullDBValue(NullValue nullValue) {
        super(nullValue, DBValueType.NULL);
    }

    public static NullDBValue getInstance() {
        if (instance == null) {
            instance = new NullDBValue(new NullValue());
        }
        return instance;
    }
}
