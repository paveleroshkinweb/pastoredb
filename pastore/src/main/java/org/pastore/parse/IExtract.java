package org.pastore.parse;

import org.pastore.exception.command.InvalidCommandException;

public interface IExtract {

    ExtractionResult extract(String name, String text) throws InvalidCommandException;

}
