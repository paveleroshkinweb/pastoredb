package org.pastore;

import org.apache.log4j.Logger;
import org.pastore.cli.CLI;
import org.pastore.cli.CLIOption;
import org.pastore.config.property.ServerTypeProperty;
import org.pastore.hook.ShutdownHook;
import org.pastore.load.Loader;
import org.pastore.logging.LoggerLoader;
import org.pastore.server.Server;
import org.pastore.server.ServerFactory;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        LoggerLoader.loadDefaultLogger();

        try {
            Map<CLIOption, Object> options = CLI.parseCLIArguments(args);
            Loader.load(options);

            ServerTypeProperty serverType = new ServerTypeProperty();

            try (Server server = ServerFactory.getServer(serverType.getValue())) {
                Runtime.getRuntime().addShutdownHook(new ShutdownHook(server));
                server.listen();
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(Main.class);
            logger.error(e);
        }
    }
}
