package org.pastore.parse;

import org.pastore.exception.client.ClientException;

public class KeyExtractor implements IExtract {

    @Override
    public ExtractionResult extract(String name, String text) throws ClientException {
        String result = StrUtils.keyNameExtract(text, ' ');
        ExtractionResult extractionResult = new ExtractionResult(result, StrUtils.slice(text, result.length()));
        return extractionResult;
    }
}
