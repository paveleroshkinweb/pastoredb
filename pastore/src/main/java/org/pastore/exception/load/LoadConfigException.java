package org.pastore.exception.load;

public class LoadConfigException extends RuntimeException {

    public LoadConfigException(String msg, Throwable e) {
        super(msg, e);
    }
}
