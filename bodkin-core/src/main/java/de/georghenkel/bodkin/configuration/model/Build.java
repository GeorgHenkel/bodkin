package de.georghenkel.bodkin.configuration.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Build {
	private Map<String, String> directories;
	private List<Collection> collections;

	public Build() {
		createDefaultCollections();
		createDefaultDirectories();
	}

	public Map<String, String> getDirectories() {
		return directories;
	}

	public void setDirectories(Map<String, String> directories) {
		this.directories = directories;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	private void createDefaultCollections() {
		collections = new ArrayList<>();
		collections.add(new Collection("posts", true, ""));
	}

	private void createDefaultDirectories() {
		directories = new HashMap<>();

		directories.put("source", ".");
		directories.put("destination", "_site");
		directories.put("collections_dir", ".");
		directories.put("plugins_dir", "_plugins");
		directories.put("layouts_dir", "_layouts");
		directories.put("data_dir", "_data");
		directories.put("includes_dir", "_includes");
	}
}
