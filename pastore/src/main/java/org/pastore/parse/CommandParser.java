package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.InvalidCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final Map<CommandType, IParse> parsers = new HashMap<>() {
        {
            put(CommandType.SET, new SetParser());
        }
    };

    public static Command parseTextToCommand(String text) throws InvalidCommand {
        String trimmedText = text.trim();
        String plainCommand = getPlainCommand(trimmedText);
        CommandType commandType = CommandType.getCommandByName(plainCommand);
        if (commandType == null) {
            throw new InvalidCommand("There is no command: " + plainCommand);
        }
        IParse commandParser = getParserByCommandType(commandType);

        return commandParser.parse(trimmedText);
    }

    public static IParse getParserByCommandType(CommandType commandType) {
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
