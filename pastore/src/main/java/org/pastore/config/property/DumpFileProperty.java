package org.pastore.config.property;


import org.pastore.config.transform.DumpFileTransform;

public class DumpFileProperty extends Property<String> {

    public DumpFileProperty() {
        super(ConfigProperty.DUMPFILE,
                "/var/lib/pastore/pastore-dump.pdb",
                new DumpFileTransform());
    }
}
