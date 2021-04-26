package org.pastore;

import org.apache.log4j.Logger;
import org.pastore.config.ConfigLoader;
import org.pastore.config.property.*;
import org.pastore.db.Database;
import org.pastore.logging.LoggerLoader;
import org.pastore.server.Server;
import org.pastore.server.ServerFactory;

public class Main {

    public static void main(String[] args) throws Exception{
        ConfigLoader.loadConfig(args[0]);
        LoggerLoader.loadLogger();
        try {

            ServerTypeProperty serverType = (ServerTypeProperty) PropertyFactory.getProperty(ConfigProperty.SERVER_TYPE);
            DumpFileProperty dumpFile = (DumpFileProperty) PropertyFactory.getProperty(ConfigProperty.DUMPFILE);
            HistoryFileProperty historyfile = (HistoryFileProperty) PropertyFactory.getProperty(ConfigProperty.HISTORYFILE);
            DatabasesProperty databases = (DatabasesProperty) PropertyFactory.getProperty(ConfigProperty.DATABASES);
            SaveIntervalProperty saveInterval = (SaveIntervalProperty) PropertyFactory.getProperty(ConfigProperty.SAVE);

            Database.init(dumpFile, historyfile, databases, saveInterval);

            try (Server server = ServerFactory.getServer(serverType.getValue())) {
                server.listen();
            }

        } catch (Exception e) {
            Logger logger = Logger.getLogger(Main.class);
            logger.error("Exception occurred", e);
        }
    }
}
