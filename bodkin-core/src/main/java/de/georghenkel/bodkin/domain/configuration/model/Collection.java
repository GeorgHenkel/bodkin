package de.georghenkel.bodkin.domain.configuration.model;

public class Collection {
  private String name;
  private boolean output;
  private String permalink;

  public Collection(final String name, final boolean output, final String permalink) {
    this.name = name;
    this.output = output;
    this.permalink = permalink;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public boolean isOutput() {
    return output;
  }

  public void setOutput(final boolean output) {
    this.output = output;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(final String permalink) {
    this.permalink = permalink;
  }
}
