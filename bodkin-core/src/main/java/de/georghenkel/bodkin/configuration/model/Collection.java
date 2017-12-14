package de.georghenkel.bodkin.configuration.model;

public class Collection {
	private String name;
	private boolean output;
	private String permalink;

	public Collection(String name, boolean output, String permalink) {
		this.name = name;
		this.output = output;
		this.permalink = permalink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
}
