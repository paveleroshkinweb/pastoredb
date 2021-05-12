package org.pastore.db;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.config.property.DatabasesProperty;
import org.pastore.db.job.ExpireJob;
import org.pastore.db.store.Store;
import org.pastore.exception.client.ClientException;
import org.pastore.handle.IHandle;
import org.pastore.handle.factory.IHandlerFactory;
import org.pastore.history.AbstractHistoryHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Database extends AbstractDatabase {

    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private static final Logger logger = Logger.getLogger(Database.class);

    private List<Store> db;

    public Database(AbstractHistoryHandler historyHandler, DatabasesProperty databases, IHandlerFactory handlerFactory) {
        super(historyHandler, databases, handlerFactory);
        this.init();
    }

    private void init() {
        logger.info("Initialising database...");
        List<Command> commands = this.getHistoryHandler().readCommands();
        int storeNumber = this.getDatabases().getValue();
        if (commands.size() > 0) {
            storeNumber = Integer.max(
                    commands.stream().max(Comparator.comparing(Command::getStoreNumber)).get().getStoreNumber(),
                    storeNumber
            );
        }
        this.db = createFreeStores(storeNumber);
        for (Command command : commands) {
            IHandle handler = this.getHandlerFactory().getHandler(command.getCommandType());
            try {
                handler.handle(command, null, this.getStoreByIndex(command.getStoreNumber()));
            } catch (ClientException | IOException e) {
                logger.error("Can't process command: " + command, e);
            }
        }
    }

    private List<Store> createFreeStores(int storesNumber) {
        List<Store> stores = new ArrayList<>();
        for (int i = 0; i < storesNumber; i++) {
            stores.add(new Store(this, i));
        }
        return stores;
    }

    public Store getStoreByIndex(int index) {
        return this.db.get(index);
    }

    public void setExpires(String key, int expires, int storeNumber) {
        executor.schedule(new ExpireJob(this.getStoreByIndex(storeNumber), key), expires, TimeUnit.SECONDS);
    }

    public void shutdownTimers() {
        executor.shutdownNow();
    }

    public int getStoresNumber() {
        return this.db.size();
    }

    @Override
    public void close()  {
      this.shutdownTimers();
    }
}
