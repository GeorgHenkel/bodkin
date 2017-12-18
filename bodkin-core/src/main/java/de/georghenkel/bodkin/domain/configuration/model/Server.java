package de.georghenkel.bodkin.domain.configuration.model;

public class Server {
	private int port;
	private String host;

	public Server() {
		this.port = 4000;
		this.host = "127.0.0.1";
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
