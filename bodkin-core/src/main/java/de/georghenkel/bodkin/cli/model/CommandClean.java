package de.georghenkel.bodkin.cli.model;

import com.beust.jcommander.Parameters;

@Parameters(separators = " ", commandDescription = " Clean the site (removes site output) without building")
public class CommandClean extends CommandMain {
	public static final String COMMAND_TYPE = "clean";
}
