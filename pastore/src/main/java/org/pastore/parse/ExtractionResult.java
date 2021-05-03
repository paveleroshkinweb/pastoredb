package org.pastore.parse;

public class ExtractionResult {

    private final String value;

    private final String updatedString;

    public ExtractionResult(final String value, final String updatedString) {
        this.value = value;
        this.updatedString = updatedString;
    }

    public String getValue() {
        return value;
    }

    public String getUpdatedString() {
        return updatedString;
    }
}
