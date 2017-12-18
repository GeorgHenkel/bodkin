package de.georghenkel.bodkin.interfaces.cli;

import java.util.Optional;

import com.beust.jcommander.JCommander;

import de.georghenkel.bodkin.domain.cli.model.CommandBuild;
import de.georghenkel.bodkin.domain.cli.model.CommandClean;
import de.georghenkel.bodkin.domain.cli.model.CommandMain;
import de.georghenkel.bodkin.domain.cli.model.CommandServe;

public class Cli {
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

		commander = JCommander.newBuilder().programName("bodkin").addCommand("build", build).addCommand("serve", serve)
				.addCommand("clean", clean).build();
	}
}
