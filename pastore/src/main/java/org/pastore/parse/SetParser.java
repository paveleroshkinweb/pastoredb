package org.pastore.parse;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.SetCommand;
import org.pastore.command.exception.InvalidCommand;
import org.pastore.command.option.OptionType;

import java.util.Map;

public class SetParser implements IParse {

    private static final Logger logger = Logger.getLogger(SetParser.class);

    private static final OptionType[] requiredOptions = { OptionType.VALUE, OptionType.TYPE };

    private static final OptionType[] optionalOptions = { OptionType.EXPIRES };

    public Command parse(String plainValue) throws InvalidCommand {
        try {
            String[] splitedText = plainValue.split(" ");
            String propertyName = splitedText[1];
            Map<OptionType, String> options = this.extractOptions(plainValue, requiredOptions, optionalOptions);
            SetCommand command = new SetCommand(propertyName, options);
            return command;
        } catch (InvalidCommand e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InvalidCommand("Invalid command: " + plainValue);
        }
    }
}
