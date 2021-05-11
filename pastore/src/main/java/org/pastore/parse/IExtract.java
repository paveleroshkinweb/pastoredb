package org.pastore.parse;

import org.pastore.exception.client.ClientException;

public interface IExtract {

    ExtractionResult extract(String name, String text) throws ClientException;

}
