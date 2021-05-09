package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.PropertyType;
import org.pastore.command.format.Format;
import org.pastore.command.option.OptionType;
import org.pastore.exception.client.command.InvalidCommandException;
import org.pastore.exception.client.command.MissingPropertyException;
import org.pastore.exception.client.command.RequiredOptionsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Parser implements IParse {

    public Command parse(CommandType commandType, String plainValue) throws InvalidCommandException {
        Format format = commandType.getFormat();
        PropertyType[] types = format.getFormat();
        String text = StrUtils.skipSpaces(StrUtils.skipWord(plainValue));
        Map<PropertyType, String> properties = new HashMap<>();
        for (PropertyType type : types) {
            if (text.length() == 0) {
                throw new MissingPropertyException(type.getName());
            }
            IExtract extractor = ExtractorFactory.getExtractorByType(type.getExtractorType());
            ExtractionResult extractionResult = extractor.extract(type.getName(), text);
            properties.put(type, extractionResult.getValue());
            text = StrUtils.skipSpaces(extractionResult.getUpdatedString());
        }

        OptionExtractor optionExtractor = new OptionExtractor();
        Map<OptionType, String> options = optionExtractor.extractOptions(text, format.getReqOpt(), format.getPosOpt());

        Set<OptionType> diff = new HashSet<>(format.getReqOpt());
        diff.removeAll(options.keySet());
        if (diff.size() > 0) {
            throw new RequiredOptionsException(diff);
        }

        Command newCommand = new Command(commandType, plainValue, properties, options);
        return newCommand;
    }

}
