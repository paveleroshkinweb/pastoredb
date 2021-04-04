package org.pastore;

import org.apache.log4j.Logger;
import org.pastore.config.ConfigLoader;
import org.pastore.config.property.ConfigProperty;
import org.pastore.config.property.Property;
import org.pastore.config.property.PropertyFactory;
import org.pastore.logging.LoggerLoader;
import org.pastore.server.Server;
import org.pastore.server.ServerFactory;
import org.pastore.server.ServerType;

public class Main {

    public static void main(String[] args) throws Exception{
        ConfigLoader.loadConfig(args[0]);
        LoggerLoader.loadLogger();
        try {
            Property<ServerType> serverType = PropertyFactory.getProperty(ConfigProperty.SERVER_TYPE);
            try (Server server = ServerFactory.getServer(serverType.getValue())) {
                server.listen();
            }
        } catch (Exception exception) {
            Logger logger = Logger.getLogger(Main.class);
            logger.error(exception.getMessage());
        }
    }
}
