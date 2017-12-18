package de.georghenkel.bodkin.application.bootstrap;

import java.util.Optional;

import javax.inject.Inject;

import de.georghenkel.bodkin.domain.cli.model.CommandMain;
import de.georghenkel.bodkin.domain.configuration.service.Config;
import de.georghenkel.bodkin.interfaces.cli.Cli;

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
