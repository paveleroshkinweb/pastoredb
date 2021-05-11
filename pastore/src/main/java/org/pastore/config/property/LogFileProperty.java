package org.pastore.config.property;

import org.pastore.config.transform.FileTransform;

public class LogFileProperty extends Property<String> {

    public LogFileProperty() {
        super(ConfigProperty.LOGFILE,
              "/var/log/pastore/pastore.log",
              new FileTransform());
    }
}
