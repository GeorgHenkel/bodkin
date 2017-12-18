package de.georghenkel.bodkin.configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import de.georghenkel.bodkin.cli.model.CommandMain;
import de.georghenkel.bodkin.configuration.model.Configuration;

@ApplicationScoped
public class Config {
	private Configuration configuration;

	@PostConstruct
	public void init() {
		configuration = new Configuration();
	}

	public void initConfiguration(Optional<? extends CommandMain> command) {
		File configFile = loadConfigFile(command);

		YamlConfigReader configReader = new YamlConfigReader();
		configuration = configReader.readConfig(configFile);

		// TODO: overwrite config parameter by command line
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	private File loadConfigFile(Optional<? extends CommandMain> command) {
		return command.isPresent() ? command.get().getConfig() : getDefaultConfigFile();
	}

	private File getDefaultConfigFile() {
		return Paths.get("./_config.yml").toAbsolutePath().normalize().toFile();
	}
}
