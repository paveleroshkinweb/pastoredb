package org.pastore.db.job;

import org.pastore.db.store.Store;

public class ExpireJob implements Runnable {

    private final Store store;

    private final String key;

    public ExpireJob(Store store, String key) {
        this.store = store;
        this.key = key;
    }

    @Override
    public void run() {
        store.removeValueByKey(key);
    }
}
