package de.georghenkel.bodkin.cli;

import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cli {
	private static final Logger log = LoggerFactory.getLogger(Cli.class);

	private static final String VERBOSE_OPTION = "verbose";
	private static final String FILE_OPTION = "file";

	private String[] args = null;
	private Options options = new Options();

	public Cli(final String... args) {
		this.args = args;
		this.options = generateOptions();
	}

	public void parse() {
		CommandLine cmd = generateCommandLine(options, args);

		if (cmd.hasOption("h"))
			printHelp(options);

		if (cmd.hasOption("v")) {
			log.info("Using cli argument -v=" + cmd.getOptionValue("v"));
			// Whatever you want to do with the setting goes here
		} else {
			log.warn("Missing v option");
			printHelp(options);
		}
	}

	private Options generateOptions() {
		final Option verboseOption = Option.builder("build").required(false).hasArg(false).longOpt(VERBOSE_OPTION)
				.desc("Print status with verbosity.").build();

		final Option fileOption = Option.builder("f").required(false).longOpt(FILE_OPTION).hasArg()
				.desc("File to be processed.").build();

		final Options options = new Options();
		options.addOption(verboseOption).addOption(fileOption);

		return options;
	}

	private CommandLine generateCommandLine(final Options options, final String... commandLineArguments) {
		final CommandLineParser cmdLineParser = new DefaultParser();
		CommandLine commandLine = null;

		try {
			commandLine = cmdLineParser.parse(options, commandLineArguments);
		} catch (ParseException parseException) {
			log.error("ERROR: Unable to parse command-line arguments " + Arrays.toString(commandLineArguments)
					+ " due to: " + parseException);
		}

		return commandLine;
	}

	private void printHelp(final Options options) {
		final HelpFormatter formatter = new HelpFormatter();
		final String syntax = "bodkin [options]";
		final String usageHeader = "options:";
		final String usageFooter = "";

		formatter.printHelp(syntax, usageHeader, options, usageFooter);
	}
	
	public static void main(String[] args) {
		Cli cli = new Cli();
		cli.parse();
	}
}
