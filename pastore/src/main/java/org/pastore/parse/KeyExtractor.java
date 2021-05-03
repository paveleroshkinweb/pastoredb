package org.pastore.parse;

import org.pastore.exception.command.InvalidCommandException;

public class KeyExtractor implements IExtract {

    @Override
    public ExtractionResult extract(String name, String text) throws InvalidCommandException {
        String result = StrUtils.keyNameExtract(text, ' ');
        ExtractionResult extractionResult = new ExtractionResult(result, StrUtils.slice(text, result.length()));
        return extractionResult;
    }
}
