package de.georghenkel.bodkin.application;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import de.georghenkel.bodkin.application.bootstrap.Boot;

public class Application {
	public static void main(String... args) {
		try (WeldContainer container = new Weld().initialize()) {
			container.select(Boot.class).get().startUp(args);
		}
	}
}
