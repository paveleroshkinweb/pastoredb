package org.pastore.config.property;

import org.pastore.config.transform.ServerTypeTransform;
import org.pastore.server.ServerType;

public class ServerTypeProperty extends Property<ServerType> {

    public ServerTypeProperty() {
        super(ConfigProperty.SERVER_TYPE,
              ServerType.UNENCRYPTED,
              new ServerTypeTransform());
    }
}
