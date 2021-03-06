package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.unknown.UnknownCommandException;

public class CommandParser {

    private static final IParse parser = new Parser();

    public static Command parseTextToCommand(String text) throws ClientException {
        String trimmedText = text.trim();
        String plainCommand = getPlainCommand(trimmedText);
        CommandType commandType = CommandType.getCommandByName(plainCommand);
        if (commandType == null) {
            throw new UnknownCommandException(plainCommand);
        }
        return parser.parse(commandType, trimmedText);
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
