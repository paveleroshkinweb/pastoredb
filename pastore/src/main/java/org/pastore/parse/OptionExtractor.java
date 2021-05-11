package org.pastore.parse;

import org.pastore.command.option.OptionType;
import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.InvalidOptionException;
import org.pastore.exception.client.unknown.UnknownOptionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OptionExtractor {

    public Map<OptionType, String> extractOptions(String text, Set<OptionType> reqOpt, Set<OptionType> posOpt) throws ClientException {
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
            text = StrUtils.slice(text, optionName.length() + 1);
            ExtractionResult extResult = StrUtils.stringExtract(optionName, text, '"');
            String optionValue = extResult.getValue();
            options.put(type, optionValue);
            text = StrUtils.skipSpaces(extResult.getUpdatedString());
        }

        return options;
    }




}
