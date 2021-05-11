package org.pastore.utils;

import java.io.File;
import java.io.IOException;

public class FSHelper {

    private final File file;

    public FSHelper(String path) {
        this.file = new File(path);
    }

    public boolean isFileAccessible() {
        return this.file.exists() && this.file.canWrite();
    }

    public boolean isEmpty() {
        return this.file.length() == 0;
    }

    public boolean createIfNotExist() {
        if (!this.file.exists()) {
            try {
                return this.file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        return this.isFileAccessible();
    }
}
