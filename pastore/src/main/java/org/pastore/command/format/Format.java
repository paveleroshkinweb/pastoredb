package org.pastore.command.format;

import org.pastore.command.PropertyType;
import org.pastore.command.option.OptionType;

import java.util.Set;

public class Format {

    private final PropertyType[] format;

    private final Set<OptionType> reqOpt;

    private final Set<OptionType> posOpt;

    public Format(final PropertyType[] format, final Set<OptionType> reqOpt, final Set<OptionType> posOpt) {
        this.format = format;
        this.reqOpt = reqOpt;
        this.posOpt = posOpt;
    }

    public PropertyType[] getFormat() {
        return format;
    }

    public Set<OptionType> getReqOpt() {
        return reqOpt;
    }

    public Set<OptionType> getPosOpt() {
        return posOpt;
    }
}
