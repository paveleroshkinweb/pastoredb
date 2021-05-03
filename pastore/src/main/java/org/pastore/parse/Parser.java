package org.pastore.parse;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.PropertyType;
import org.pastore.command.format.Format;
import org.pastore.command.option.OptionType;
import org.pastore.exception.command.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;


public class Parser implements IParse {

    private static final Logger logger = Logger.getLogger(Parser.class);

    public Command parse(CommandType commandType, String plainValue) throws InvalidCommandException {
        Format format = commandType.getFormat();
        PropertyType[] types = format.getFormat();
        String text = StrUtils.skipSpaces(StrUtils.skipWord(plainValue));
        Map<PropertyType, String> properties = new HashMap<>();
        for (PropertyType type : types) {
            if (text.length() == 0) {
                throw new InvalidCommandException(type.getName() + " can't be empty!");
            }
            IExtract extractor = ExtractorFactory.getExtractorByType(type.getExtractorType());
            ExtractionResult extractionResult = extractor.extract(type.getName(), text);
            properties.put(type, extractionResult.getValue());
            text = StrUtils.skipSpaces(extractionResult.getUpdatedString());
        }

        OptionExtractor optionExtractor = new OptionExtractor();
        Map<OptionType, String> options = optionExtractor.extractOptions(text, format.getReqOpt(), format.getPosOpt());

        Command newCommand = new Command(commandType, plainValue, properties, options);
        return newCommand;
    }

}
