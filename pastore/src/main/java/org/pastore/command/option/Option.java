package org.pastore.command.option;

public class Option {

    private final OptionType optionType;

    private final String plainValue;

    public Option(final OptionType optionType, final String plainValue) {
        this.optionType = optionType;
        this.plainValue = plainValue;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public String getPlainValue() {
        return plainValue;
    }
}
