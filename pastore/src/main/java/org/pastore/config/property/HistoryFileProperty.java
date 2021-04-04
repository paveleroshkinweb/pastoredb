package org.pastore.config.property;

import org.pastore.config.transform.HistoryFileTransform;

public class HistoryFileProperty extends Property<String> {

    public HistoryFileProperty() {
        super(ConfigProperty.HISTORYFILE,
                "/var/lib/pastore/pastore-history.phist",
                new HistoryFileTransform());
    }
}
