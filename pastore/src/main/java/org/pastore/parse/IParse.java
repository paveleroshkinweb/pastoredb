package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.exception.InvalidCommand;
import org.pastore.command.option.OptionType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IParse {

    Command parse(String plainValue) throws InvalidCommand;

    default Map<OptionType, String> extractOptions(String plainValue, OptionType[] requiredOptions, OptionType[] optionalOptions) throws InvalidCommand {
        Map<OptionType, String> options = new HashMap<>();
        for (OptionType requiredOption : requiredOptions) {
            String optionName = requiredOption.getName();
            String optionValue = extractOptionValue(optionName, plainValue);

            if (optionValue == null) {
                throw new InvalidCommand("Missing required option: " + optionName + "!");
            }
            options.put(requiredOption, optionValue);
        }
        for (OptionType optionalOption : optionalOptions) {
            String optionName = optionalOption.getName();
            String optionValue = extractOptionValue(optionName, plainValue);
            if (optionValue != null) {
                options.put(optionalOption, optionValue);
            }
        }
        return options;
    }

    default String extractOptionValue(String optionName, String text) {
        try {
            Pattern pattern = Pattern.compile(optionName + "=\"(.+?)\"");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IllegalStateException e) {}
        return null;
    }
}
