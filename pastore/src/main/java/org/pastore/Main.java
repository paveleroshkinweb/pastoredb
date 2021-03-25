package org.pastore;

import org.pastore.config.ConfigLoader;
import org.pastore.logging.LoggerLoader;

public class Main {

    public static void main(String[] args) throws Exception{
        try {
            ConfigLoader.loadConfig(args[0]);
            LoggerLoader.loadLogger();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
