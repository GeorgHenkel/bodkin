package de.georghenkel.bodkin.domain.cli.model;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = " ", commandDescription = "Build your site")
public class CommandBuild extends CommandMain {
  public static final String COMMAND_TYPE = "build";

  @Parameter(names = {"--baseurl", "-b"}, description = "Serve the website from the given base URL")
  private String baseurl;

  public String getBaseurl() {
    return baseurl;
  }

  public void setBaseurl(final String baseurl) {
    this.baseurl = baseurl;
  }
}
