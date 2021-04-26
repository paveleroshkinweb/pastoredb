package org.pastore.command;

import org.pastore.command.option.OptionType;

import java.util.Map;

public class SetCommand extends Command {

    public SetCommand(String property, Map<OptionType, String> options) {
        super(CommandType.SET, property, options);
    }

}
