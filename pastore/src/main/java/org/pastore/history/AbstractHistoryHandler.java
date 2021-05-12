package org.pastore.history;

import org.pastore.command.Command;
import org.pastore.config.property.HistoryFileProperty;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract class AbstractHistoryHandler implements Closeable {

    private HistoryFileProperty historyFile;

    public AbstractHistoryHandler(HistoryFileProperty historyFile) throws IOException {
        this.historyFile = historyFile;
    }

    public HistoryFileProperty getHistoryFile() {
        return historyFile;
    }

    public abstract void writeCommand(Command command);

    public abstract List<Command> readCommands();
}
