package org.pastore.command;

import org.pastore.command.option.OptionType;

import java.util.Map;

public class SetCommand extends Command {

    private static final OptionType[] AVAILABLE_OPTIONS = { OptionType.TYPE, OptionType.VALUE, OptionType.EXPIRES }

    public SetCommand(String plainCommand, String property, Map<OptionType, String> options) {
        super(CommandType.SET, plainCommand, property, options);
    }

    public static OptionType[] getAvailableOptions() {
        return AVAILABLE_OPTIONS;
    }

}
