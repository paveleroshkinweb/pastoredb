package org.pastore.parse;

import org.pastore.command.option.OptionType;
import org.pastore.exception.client.command.ExtraOptionException;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.exception.client.command.InvalidOptionException;
import org.pastore.exception.client.command.UnknownOptionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OptionExtractor {

    public Map<OptionType, String> extractOptions(String text, Set<OptionType> reqOpt, Set<OptionType> posOpt) throws InvalidCommandException {
        Map<OptionType, String> options = new HashMap<>();

        while (text.length() > 0) {
            String optionName = StrUtils.keyNameExtract(text, '=');
            OptionType type = OptionType.getTypeByName(optionName);
            if (type == null) {
                throw new UnknownOptionException(optionName);
            }
            if (! reqOpt.contains(type) && ! posOpt.contains(type)) {
                throw new InvalidOptionException(type);
            }
            if (options.containsKey(type)) {
                throw new ExtraOptionException(type);
            }
            text = StrUtils.slice(text, optionName.length() + 1);
            String optionValue = StrUtils.stringExtract(optionName, text, '"');
            options.put(type, optionValue);
            text = StrUtils.skipSpaces(StrUtils.slice(text, optionValue.length() + 2));
        }

        return options;
    }




}
