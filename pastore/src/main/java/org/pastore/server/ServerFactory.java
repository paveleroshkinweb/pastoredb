package org.pastore.server;

import org.pastore.config.exception.InvalidConfigPropertyException;
import org.pastore.config.property.ConfigProperty;
import org.pastore.config.property.Property;
import org.pastore.config.property.PropertyFactory;

import java.io.IOException;


public class ServerFactory {

    private ServerFactory() {}

    public static Server getServer(ServerType serverType) throws InvalidConfigPropertyException, IOException {
        Property<String> bindAddress = PropertyFactory.getProperty(ConfigProperty.BIND);
        Property<Integer> port = PropertyFactory.getProperty(ConfigProperty.PORT);
        Property<Integer> maxClients = PropertyFactory.getProperty(ConfigProperty.MAX_CLIENTS);
        Property<String> dumpfile = PropertyFactory.getProperty(ConfigProperty.DUMPFILE);
        Property<String> historyfile = PropertyFactory.getProperty(ConfigProperty.HISTORYFILE);
        Property<Boolean> isPasswordProtected = PropertyFactory.getProperty(ConfigProperty.PASSWORD_PROTECTED);
        Property<Integer> timeout = PropertyFactory.getProperty(ConfigProperty.TIMEOUT);
        Property<Integer> backlog = PropertyFactory.getProperty(ConfigProperty.TCP_BACKLOG);
        Property<Integer> databases = PropertyFactory.getProperty(ConfigProperty.DATABASES);
        Property<Integer> saveInterval = PropertyFactory.getProperty(ConfigProperty.SAVE);
        return new ServerBuilder()
                    .setServerType(serverType)
                    .setBindAddress(bindAddress.getValue())
                    .setPort(port.getValue())
                    .setMaxClients(maxClients.getValue())
                    .setDumpfile(dumpfile.getValue())
                    .setHistoryfile(historyfile.getValue())
                    .setPasswordProtected(isPasswordProtected.getValue())
                    .setTimeout(timeout.getValue())
                    .setBacklog(backlog.getValue())
                    .setDatabases(databases.getValue())
                    .setSaveInterval(saveInterval.getValue())
                    .build();
    }
}
