package org.pastore.parse;

import org.pastore.exception.client.ClientException;
import org.pastore.exception.client.command.*;
import org.pastore.db.value.DBValueType;
import org.pastore.exception.client.format.ArrayFormatException;
import org.pastore.exception.client.required.EmptyKeyException;
import org.pastore.exception.client.format.EscapeException;
import org.pastore.exception.client.format.InvalidCharException;
import org.pastore.exception.client.format.UnbalancedQuotesException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StrUtils {

    private static final String INVALID_INT = "Invalid integer format: %s";

    private static final List<Character> BLACK_LIST = Arrays.asList(new Character[]{ '"', '\'' });

    public static String skipSpaces(String text) {
        int index = 0;
        while (index < text.length() && text.charAt(index) == ' ') {
            index++;
        }
        return text.substring(index);
    }

    public static String skipWord(String text) {
        int pointer = 0;
        while (pointer < text.length() && text.charAt(pointer) != ' ') {
            pointer++;
        }
        return text.substring(pointer);
    }

    public static String slice(String text, int startIndex) {
        if (startIndex >= text.length()) {
            return "";
        }
        return text.substring(startIndex);
    }

    public static String keyNameExtract(String text, Character stopCharacter) throws ClientException {
        StringBuilder result = new StringBuilder();
        int pointer = 0;
        while (pointer < text.length() && text.charAt(pointer) != stopCharacter) {
            if (BLACK_LIST.contains(text.charAt(pointer))) {
                throw new InvalidCharException(text.charAt(pointer));
            }
            result.append(text.charAt(pointer));
            pointer++;
        }

        if (result.length() == 0) {
            throw new EmptyKeyException();
        }

        return result.toString();
    }

    public static ExtractionResult stringExtract(String name, String text, Character quote) throws ClientException {
        StringBuilder result = new StringBuilder();
        if (text.charAt(0) != quote) {
            throw new UnbalancedQuotesException(
                    name + " value should be in " + quote + " quotes"
            );
        }
        int pointer = 1;
        boolean closed = false;
        boolean encodeMode = false;
        while (pointer < text.length()) {
            if (encodeMode && (text.charAt(pointer) != quote && text.charAt(pointer) != '\\')) {
                throw new EscapeException("Please escape \\ symbol!");
            }
            if (encodeMode && (text.charAt(pointer) == quote || text.charAt(pointer) == '\\')) {
                encodeMode = false;
            }
            else if (text.charAt(pointer) == '\\') {
                encodeMode = true;
                pointer++;
                continue;
            }
            else if (text.charAt(pointer) == quote && ! encodeMode) {
                closed = true;
                pointer++;
                break;
            }
            result.append(text.charAt(pointer));
            pointer++;
        }
        if (! closed) {
            throw new UnbalancedQuotesException(name + " invalid value! Please make sure that you closed " + quote + " quotes!");
        }
        if (result.length() == 0) {
            throw new EmptyKeyException(name);
        }
        return new ExtractionResult(result.toString(), slice(text, pointer));
    }

    public static List<Integer> parseStringToIntList(String text) throws ClientException {
        if (text.charAt(0) != '[' || text.charAt(text.length() - 1) != ']') {
            throw new ArrayFormatException();
        }
        List<Integer> results = new LinkedList<>();
        text = text.substring(1, text.length()-1);
        while (text.length() > 0) {
            text = skipSpaces(text);
            Integer nextInt = parseNextIntInArray(text);
            results.add(nextInt);
            text = skipSpaces(slice(text, String.valueOf(nextInt).length() + 1));
        }
        return results;
    }

    public static List<String> parseStringToStrList(String text) throws ClientException {
        if (text.charAt(0) != '[' || text.charAt(text.length() - 1) != ']') {
            throw new ArrayFormatException();
        }
        List<String> results = new LinkedList<>();
        text = text.substring(1, text.length()-1);
        while (text.length() > 0) {
            text = skipSpaces(text);
            ExtractionResult extractionResult = stringExtract(DBValueType.LIST_STR .getPrefix() + " elements", text, '\'');
            results.add(extractionResult.getValue());
            text = skipSpaces(slice(extractionResult.getUpdatedString(), 1));
        }
        return results;
    }

    public static Integer parseStringToInt(String text) throws ClientException {
        try {
            Integer value = Integer.valueOf(text);
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(String.format(INVALID_INT, text));
        }
    }

    private static Integer parseNextIntInArray(String text) throws ClientException {
        int commaIndex = text.indexOf(",");
        String strToProcess;
        if (commaIndex == -1) {
            strToProcess = text;
        } else {
            strToProcess = text.substring(0, commaIndex);
        }
        try {
            Integer value = Integer.valueOf(strToProcess);
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidIntegerException(String.format(INVALID_INT, strToProcess));
        }
    }

}
