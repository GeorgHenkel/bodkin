package de.georghenkel.bodkin.domain.configuration.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import de.georghenkel.bodkin.domain.cli.model.CommandMain;
import de.georghenkel.bodkin.domain.configuration.model.Configuration;

@ApplicationScoped
public class Config {
	@Inject
	Logger log;

	private Configuration configuration;

	@PostConstruct
	public void init() {
		configuration = new Configuration();
	}

	public void initConfiguration(Optional<? extends CommandMain> command) {
		try (InputStream configFileStream = loadConfigFile(command)) {
			YamlConfigReader configReader = new YamlConfigReader();
			configuration = configReader.readConfig(configFileStream);

			// TODO: overwrite config parameter by command line
		} catch (IOException ex) {
			log.error("Error handling config file", ex);
			throw new RuntimeException(ex);
		}
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	private InputStream loadConfigFile(Optional<? extends CommandMain> command) {
		try {
			File configFile = command.isPresent() ? command.get().getConfig() : getDefaultConfigFile();
			log.info("Loading configuration: " + configFile.getAbsolutePath());
			return new FileInputStream(configFile);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	private File getDefaultConfigFile() {
		return Paths.get("./_config.yml").toAbsolutePath().normalize().toFile();
	}
}
