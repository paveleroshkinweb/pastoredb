package org.pastore.db;

public class ExpireJob implements Runnable {

    private final String key;

    private final int storeNumber;

    public ExpireJob(String key, int storeNumber) {
        this.key = key;
        this.storeNumber = storeNumber;
    }

    @Override
    public void run() {
        Database.getInstance().getStoreByIndex(this.storeNumber).removeValueByKey(this.key);
    }
}
