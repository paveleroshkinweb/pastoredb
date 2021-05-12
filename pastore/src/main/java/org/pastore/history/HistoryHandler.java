package org.pastore.history;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.option.OptionType;
import org.pastore.config.property.HistoryFileProperty;
import org.pastore.exception.history.FileLockedException;
import org.pastore.exception.history.HistoryException;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends AbstractHistoryHandler {

    private static final Logger logger = Logger.getLogger(HistoryHandler.class);

    private ObjectOutputStream outputStream;

    private FileLock lock;

    public HistoryHandler(HistoryFileProperty historyFile) throws IOException{
        super(historyFile);
        this.acquireLocks();
    }

    private void acquireLocks() throws IOException {
        Path path = Paths.get(this.getHistoryFile().getValue());
        FileChannel writeChannel = FileChannel.open(path, StandardOpenOption.APPEND);
        try {
            this.lock = writeChannel.tryLock();
            if (this.lock != null) {
                OutputStream out = Channels.newOutputStream(writeChannel);
                this.outputStream = new ObjectOutputStream(out);
            } else {
                throw new FileLockedException("Can't acquire lock to " + this.getHistoryFile().getValue());
            }
        } catch (OverlappingFileLockException e) {
            throw new FileLockedException("Can't acquire lock to " + this.getHistoryFile().getValue(), e);
        }
    }

    @Override
    public void writeCommand(Command command) {
        // remove all fields that should not be serialized
        if (command.getOptions() != null) {
            command.getOptions().remove(OptionType.EXPIRES);
        }
        try {
            this.outputStream.writeObject(command);
        } catch (IOException e) {
            throw new HistoryException("Can't write command to file", e);
        }
    }

    @Override
    public List<Command> readCommands() {
        List<Command> commands = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.getHistoryFile().getValue()))) {
            while (true) {
                try {
                    Command command = (Command) in.readObject();
                    commands.add(command);
                } catch (ClassNotFoundException | IOException e) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Can't read history file!", e);
        }
        return commands;

    }

    @Override
    public void close() throws IOException {
        if (this.lock != null) {
            this.lock.release();
        }
        if (this.outputStream != null) {
            this.outputStream.close();
        }
    }
}

