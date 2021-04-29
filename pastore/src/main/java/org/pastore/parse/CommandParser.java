package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.InvalidCommandException;
import org.pastore.command.exception.UnknownCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final Map<CommandType, Parser> parsers = new HashMap<>();

    public static Command parseTextToCommand(String text) throws InvalidCommandException {
        String trimmedText = text.trim();
        String plainCommand = getPlainCommand(trimmedText);
        CommandType commandType = CommandType.getCommandByName(plainCommand);
        if (commandType == null) {
            throw new UnknownCommandException(plainCommand);
        }
        Parser commandParser = getParserByCommandType(commandType);
        return commandParser.parse(trimmedText);
    }

    public static Parser getParserByCommandType(CommandType commandType) {
        if (! parsers.containsKey(commandType)) {
            if (commandType == CommandType.SET) {
                parsers.put(commandType, new SetCommandParser());
            }
        }
        return parsers.get(commandType);
    }

    private static String getPlainCommand(String text) {
        try {
            int i = text.indexOf(" ");
            String word = text.substring(0, i);
            return word;
        } catch (StringIndexOutOfBoundsException e) {
            return text;
        }
    }
}
