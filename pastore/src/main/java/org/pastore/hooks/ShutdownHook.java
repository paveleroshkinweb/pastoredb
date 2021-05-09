package org.pastore.hooks;

import org.pastore.db.Database;

public class ShutdownHook extends Thread {

    @Override
    public void run() {
        Database.getInstance().shutdownTimers();
    }
}
