package org.pastore.parse;

import org.pastore.command.Command;
import org.pastore.command.CommandType;
import org.pastore.exception.client.ClientException;

public interface IParse {

    Command parse(CommandType commandType, String plainValue) throws ClientException;
}
