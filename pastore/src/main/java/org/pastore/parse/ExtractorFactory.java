package org.pastore.parse;

import java.util.HashMap;
import java.util.Map;

public class ExtractorFactory {

    private static final Map<ExtractorType, IExtract> extractors = new HashMap<>() {
        {
            put(ExtractorType.KEY, new KeyExtractor());
            put(ExtractorType.STRING, new StringExtractor());
        }
    };

    public static IExtract getExtractorByType(ExtractorType type) {
        return extractors.get(type);
    }
}
