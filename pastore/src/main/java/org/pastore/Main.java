package org.pastore;

import org.apache.log4j.Logger;
import org.pastore.config.Loader;
import org.pastore.config.property.*;
import org.pastore.db.Database;
import org.pastore.server.Server;
import org.pastore.server.ServerFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        String configPath = args.length > 0 ? args[0] : null;
        Loader.load(configPath);

        ServerTypeProperty serverType = Loader.getServerTypeProperty();
        DumpFileProperty dumpFile = Loader.getDumpFileProperty();
        HistoryFileProperty historyFile = Loader.getHistoryFileProperty();
        DatabasesProperty databases = Loader.getDatabasesProperty();
        SaveIntervalProperty saveInterval = Loader.getSaveIntervalProperty();

        try {
            Database.init(dumpFile, historyFile, databases, saveInterval);
            try (Server server = ServerFactory.getServer(serverType.getValue())) {
                server.listen();
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(Main.class);
            logger.error("Unexpected exception occurred", e);
        }
    }
}
