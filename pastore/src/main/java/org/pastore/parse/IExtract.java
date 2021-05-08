package org.pastore.parse;

import org.pastore.exception.client.command.InvalidCommandException;

public interface IExtract {

    ExtractionResult extract(String name, String text) throws InvalidCommandException;

}
