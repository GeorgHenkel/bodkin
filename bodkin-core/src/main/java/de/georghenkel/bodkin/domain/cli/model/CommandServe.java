package de.georghenkel.bodkin.domain.cli.model;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = " ", commandDescription = "Serve your site locally")
public class CommandServe extends CommandBuild {
	public static final String COMMAND_TYPE = "serve";

	@Parameter(names = { "--port", "-P" }, description = "Port to listen on.")
	private String port;
	@Parameter(names = { "--host", "-H" }, description = "Host to bind to.")
	private String host;
	@Parameter(names = "--no-build", description = "Skip the initial site build.")
	private boolean skipBuild = false;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isSkipBuild() {
		return skipBuild;
	}

	public void setSkipBuild(boolean skipBuild) {
		this.skipBuild = skipBuild;
	}
}
