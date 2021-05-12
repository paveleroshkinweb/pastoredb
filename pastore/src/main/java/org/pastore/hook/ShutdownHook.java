package org.pastore.hook;

import org.apache.log4j.Logger;
import org.pastore.server.Server;

import java.io.IOException;

public class ShutdownHook extends Thread {

    private static final Logger logger = Logger.getLogger(ShutdownHook.class);

    private Server server;

    public ShutdownHook(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        logger.info("Shutdowning server");
        try {
            this.server.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
