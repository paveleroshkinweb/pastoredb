package org.pastore.exception.config;

import org.pastore.exception.BaseException;

public class ConfigException extends BaseException {

    private static final int CODE = 2;

    private static final String ERROR_PREFIX = "Config error";

    public ConfigException(String msg) {
        super(ERROR_PREFIX, CODE, msg);
    }
}