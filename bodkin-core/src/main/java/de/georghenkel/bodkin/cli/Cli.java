package de.georghenkel.bodkin.cli;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;

import de.georghenkel.bodkin.cli.model.CommandBuild;
import de.georghenkel.bodkin.cli.model.CommandClean;
import de.georghenkel.bodkin.cli.model.CommandMain;
import de.georghenkel.bodkin.cli.model.CommandServe;

public class Cli {
	private static final Logger log = LoggerFactory.getLogger(Cli.class);

	private JCommander commander;

	public Cli parse(String... args) {
		initCommander();
		commander.parse(args);
		
		return this;
	}

	public <T extends CommandMain> Optional<T> getCommandType() {
		T value = null;
		
		if (commander != null) {
			String parsedCommand = commander.getParsedCommand();
			JCommander command = commander.getCommands().get(parsedCommand);
			
			value = (T) command.getObjects().get(0);
		} 
		
		return Optional.ofNullable(value);
	}

	public void initCommander() {
		CommandBuild build = new CommandBuild();
		CommandServe serve = new CommandServe();
		CommandClean clean = new CommandClean();

		commander =  JCommander.newBuilder().programName("bodkin").addCommand("build", build).addCommand("serve", serve)
				.addCommand("clean", clean).build();
	}
}
