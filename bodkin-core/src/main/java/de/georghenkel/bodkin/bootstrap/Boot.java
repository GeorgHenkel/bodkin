package de.georghenkel.bodkin.bootstrap;

import java.util.Optional;

import javax.inject.Inject;

import de.georghenkel.bodkin.cli.Cli;
import de.georghenkel.bodkin.cli.model.CommandMain;
import de.georghenkel.bodkin.configuration.Config;
import de.georghenkel.bodkin.configuration.model.Configuration;

public class Boot {
	Config config;

	@Inject
	public Boot(Config config) {
		this.config = config;
	}

	public void startUp(String... args) {
		Optional<? extends CommandMain> command = new Cli().parse(args).getCommandType();
		config.initConfiguration(command);
	}
}
