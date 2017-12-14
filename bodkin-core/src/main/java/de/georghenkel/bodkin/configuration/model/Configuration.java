package de.georghenkel.bodkin.configuration.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configuration {
	private String baseurl;
	private Server server;
	private Build build;
	private List<Default> defaults;
	private Map<String, Object> values;

	public Configuration() {
		this.server = new Server();
		this.build = new Build();

		defaults = new ArrayList<>();
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}

	public List<Default> getDefaults() {
		return defaults;
	}

	public void setDefaults(List<Default> defaults) {
		this.defaults = defaults;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
}
