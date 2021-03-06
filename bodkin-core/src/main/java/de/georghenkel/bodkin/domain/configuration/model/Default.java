package de.georghenkel.bodkin.domain.configuration.model;

import java.util.Map;

public class Default {
  private String path;
  private String type;
  private Map<String, Object> values;

  public String getPath() {
    return path;
  }

  public void setPath(final String path) {
    this.path = path;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public void setValues(final Map<String, Object> values) {
    this.values = values;
  }
}
