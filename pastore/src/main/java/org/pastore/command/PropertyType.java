package org.pastore.command;

import org.pastore.parse.ExtractorType;

public enum PropertyType {

    KEY(ExtractorType.KEY, "key"),
    VALUE(ExtractorType.STRING, "value");

    private final ExtractorType extractorType;

    private final String name;

    PropertyType(final ExtractorType extractorType, final String name) {
        this.extractorType = extractorType;
        this.name = name;
    }

    public ExtractorType getExtractorType() {
        return extractorType;
    }

    public String getName() {
        return this.name;
    }
}
