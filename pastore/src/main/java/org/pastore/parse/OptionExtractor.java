package org.pastore.parse;

import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.clientexception.command.InvalidOptionException;
import org.pastore.clientexception.command.UnknownOptionException;
import org.pastore.command.option.OptionType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptionExtractor {

    public Map<OptionType, String> extractOptions(String text, Set<OptionType> reqOpt, Set<OptionType> posOpt) throws InvalidCommandException {
        Set<OptionType> missedRequiredOptions = new HashSet<>(reqOpt);
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
            if (reqOpt.contains(type)) {
                reqOpt.remove(type);
            }
            text = StrUtils.slice(text, optionName.length() + 1);
            String optionValue = StrUtils.stringExtract(optionName, text, '"');
            options.put(type, optionValue);
            text = StrUtils.skipSpaces(StrUtils.slice(text, optionValue.length() + 2));
        }

        return options;
    }




}
