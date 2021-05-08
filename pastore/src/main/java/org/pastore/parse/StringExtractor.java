package org.pastore.parse;

import org.pastore.exception.client.command.InvalidCommandException;

public class StringExtractor implements IExtract {

    @Override
    public ExtractionResult extract(String name, String text) throws InvalidCommandException {
        String result = StrUtils.stringExtract(name, text, '"');
        ExtractionResult extractionResult = new ExtractionResult(result, StrUtils.slice(text, result.length() + 2));
        return extractionResult;
    }
}
