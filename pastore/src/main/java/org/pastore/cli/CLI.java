package org.pastore.cli;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.pastore.common.ExitCodes;
import org.pastore.load.LoaderType;

import java.util.HashMap;
import java.util.Map;

public class CLI {

    private static final Logger logger = Logger.getLogger(CLI.class);

    public static Map<CLIOption, Object> parseCLIArguments(String[] args) throws ParseException {
        Map<CLIOption, Object> arguments = new HashMap<>();
        Options options = new Options();

        Option loaderTypeOption = new Option("l", "loadType", true, "Loader type");
        loaderTypeOption.setArgs(1);
        loaderTypeOption.setArgName(CLIOption.LOAD_TYPE.getOptionName());
        loaderTypeOption.setOptionalArg(true);

        Option configPathOption = new Option("c", "configPath", true, "Config path");
        configPathOption.setArgs(1);
        configPathOption.setArgName(CLIOption.CONFIG_PATH.getOptionName());
        loaderTypeOption.setOptionalArg(true);

        options.addOption(loaderTypeOption);
        options.addOption(configPathOption);

        CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = cmdLineParser.parse(options, args);

        if (commandLine.hasOption(CLIOption.LOAD_TYPE.getOptionName())) {
            String loadType = (String) commandLine.getParsedOptionValue(CLIOption.LOAD_TYPE.getOptionName());
            LoaderType loaderType = LoaderType.getLoaderTypeByName(loadType);
            if (loaderType == null) {
                logger.error(CLIOption.LOAD_TYPE.getOptionName() + " should be one of: " + LoaderType.availableLoaders());
                System.exit(ExitCodes.INVALID_CLI_ARGUMENTS);
            }
            arguments.put(CLIOption.LOAD_TYPE, loaderType);
        } else {
            arguments.put(CLIOption.LOAD_TYPE, LoaderType.FILE);
        }

        if (commandLine.hasOption(CLIOption.CONFIG_PATH.getOptionName())) {
            String configPath = (String) commandLine.getParsedOptionValue(CLIOption.CONFIG_PATH.getOptionName());
            arguments.put(CLIOption.CONFIG_PATH, configPath);
        }

        return arguments;
    }
}
