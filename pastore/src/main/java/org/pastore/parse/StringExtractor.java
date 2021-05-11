package org.pastore.parse;

import org.pastore.exception.client.ClientException;

public class StringExtractor implements IExtract {

    @Override
    public ExtractionResult extract(String name, String text) throws ClientException {
        ExtractionResult extractionResult = StrUtils.stringExtract(name, text, '"');
        return extractionResult;
    }
}
