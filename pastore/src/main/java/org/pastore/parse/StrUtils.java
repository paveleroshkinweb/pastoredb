package org.pastore.parse;

import org.pastore.clientexception.command.EmptyKeyException;
import org.pastore.clientexception.command.InvalidCharException;
import org.pastore.clientexception.command.InvalidCommandException;
import org.pastore.clientexception.command.UnbalancedQuotesException;

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

    public static String keyNameExtract(String text, Character stopCharacter) throws InvalidCommandException {
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

    public static String stringExtract(String name, String text, Character quote) throws InvalidCommandException {
        StringBuilder result = new StringBuilder();
        if (text.charAt(0) != quote) {
            throw new InvalidCommandException(name + " value should be in " + quote + " quotes!");
        }
        int pointer = 1;
        boolean closed = false;
        boolean encodeMode = false;
        while (pointer < text.length()) {
            if (encodeMode && (text.charAt(pointer) != quote || text.charAt(pointer) != '\\')) {
                throw new InvalidCommandException("Please escape \\ symbol!");
            }
            if (encodeMode && (text.charAt(pointer) == quote || text.charAt(pointer) == '\\')) {
                encodeMode = false;
            }
            else if (text.charAt(pointer) == '\\') {
                encodeMode = true;
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
            throw new InvalidCommandException(name + " can't be empty!");
        }
        return result.toString();
    }

    public static List<Integer> parseStringToIntList(String text) throws InvalidCommandException{
        if (text.charAt(0) != '[' || text.charAt(text.length() - 1) != ']') {
            throw new InvalidCommandException("Array must be wrapped with []");
        }
        List<Integer> results = new LinkedList<>();
        text = text.substring(1, text.length()-1);
        while (text.length() > 0) {
            Integer nextInt = parseNextIntInArray(text);
            results.add(nextInt);
            text = StrUtils.slice(text, String.valueOf(nextInt).length() + 1);
        }
        return results;
    }

    public static List<String> parseStringToStrList(String text) throws InvalidCommandException {
        if (text.charAt(0) != '[' || text.charAt(text.length() - 1) != ']') {
            throw new InvalidCommandException("Array must be wrapped with []");
        }
        List<String> results = new LinkedList<>();
        text = text.substring(1, text.length()-1);
        while (text.length() > 0) {
            String nextStr = StrUtils.stringExtract("StrArray elements", text, '\'');
            results.add(nextStr);
            text = StrUtils.slice(text, nextStr.length() + 3);
        }
        return results;
    }

    public static Integer parseStringToInt(String text) throws InvalidCommandException {
        try {
            Integer value = Integer.valueOf(text);
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(String.format(INVALID_INT, text));
        }
    }

    private static Integer parseNextIntInArray(String text) throws InvalidCommandException {
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
            throw new InvalidCommandException(strToProcess + " is not a valid integer");
        }
    }

}
