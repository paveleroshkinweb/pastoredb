package org.pastore.config.exception;

public class InvalidConfigFileException extends Exception{

    private final static String errMessage = "Invalid config file, please check if file exists and you have access to it.";

    public InvalidConfigFileException() {
        super(errMessage);
    }
}
