package org.pastore.parse;

import org.apache.log4j.Logger;
import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.command.exception.*;
import org.pastore.command.option.OptionType;

import java.util.*;

public abstract class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class);

    private static final Set<Character> PROP_BLACKLIST_CHARS = new HashSet<>(
            Arrays.asList(new Character[] {'\'', '"'} )
    );

    private final CommandType commandType;

    private final Set<OptionType> requiredOptions;

    private final Set<OptionType> additionalOptions;

    public Parser(CommandType commandType, Set<OptionType> requiredOptions, Set<OptionType> additionalOptions) {
        this.commandType = commandType;
        this.requiredOptions = requiredOptions;
        this.additionalOptions = additionalOptions;
    }

    public abstract Command parse(String plainValue) throws InvalidCommandException;

    public CommandType getCommandType() {
        return commandType;
    }

    public Map<OptionType, String> extractOptions(String text, int skip) throws InvalidCommandException {
        Map<OptionType, String> options = new HashMap<>();
        Set<OptionType> missedRequiredOptions = new HashSet<>(this.requiredOptions);
        String slicedStr = sliceString(text, skip);
        while (! slicedStr.isEmpty()) {
            String optionStr = extractNextOptionName(slicedStr);
            if (optionStr == null) {
                break;
            }
            OptionType optionType = OptionType.getTypeByName(optionStr);
            if (optionType == null) {
                throw new UnknownOptionException(optionStr);
            }
            if (! requiredOptions.contains(optionType) && ! additionalOptions.contains(optionType)) {
                throw new InvalidOptionException(optionType);
            }
            slicedStr = slicedStr.substring(optionStr.length() + 1);
            String optionValue = extractNextOptionValue(slicedStr);
            if (optionValue == null) {
                throw new InvalidCommandException("Invalid key " + optionStr + " value!");
            }
            options.put(optionType, optionValue);
            missedRequiredOptions.remove(optionType);
            slicedStr = slicedStr.substring(optionValue.length() + 2);
            if (! slicedStr.isEmpty() && slicedStr.charAt(0) != ' ') {
                throw new InvalidCommandException("Invalid " + optionStr + " value!");
            }
            slicedStr = skipSpaces(slicedStr);
        }
        if (! missedRequiredOptions.isEmpty()) {
            throw new RequiredOptionsException(missedRequiredOptions);
        }
        return options;
    }

    public String extractProperty(String plainText) throws InvalidCommandException {
        int spaceIndex = plainText.indexOf(" ");
        if (spaceIndex == -1) {
            throw new EmptyPropertyException();
        }
        plainText = skipSpaces(plainText.substring(spaceIndex));
        StringBuilder propertyName = new StringBuilder();
        for (int i = 0; i < plainText.length() && plainText.charAt(i) != ' '; i++) {
            char symbol = plainText.charAt(i);
            if (PROP_BLACKLIST_CHARS.contains(symbol)) {
                throw new InvalidCharInKeyException(symbol);
            }
            propertyName.append(symbol);
        }
        String property = propertyName.toString();
        if (CommandType.getCommandByName(property) != null) {
            throw new RegisteredKeywordException(property);
        }
        return property;
    }

    private String skipSpaces(String text) {
        int index = 0;
        while (index < text.length() && text.charAt(index) == ' ') {
            index++;
        }
        return text.substring(index);
    }

    private String sliceString(String text, int skip) {
        while (skip > 0) {
            text = skipWord(text);
            text = skipSpaces(text);
            skip--;
        }
        return text;
    }

    private String skipWord(String text) {
        int pointer = 0;
        while (pointer < text.length() && text.charAt(pointer) != ' ') {
            pointer++;
        }
        return text.substring(pointer);
    }

    private String extractNextOptionName(String text) {
        int index = text.indexOf("=");
        if (index == -1) {
            return null;
        }
        return text.substring(0, index);
    }

    private String extractNextOptionValue(String text) {
        if (text.length() <= 2 || text.charAt(0) != '"') {
            return null;
        }
        int i = 1;
        boolean closed = false;
        while (i < text.length()) {
            if (text.charAt(i) == '"' && text.charAt(i-1) != '\\') {
                closed = true;
                break;
            }
            i++;
        }
        if (! closed || i == 1) {
            return null;
        }
        return text.substring(1, i);
    }
}
