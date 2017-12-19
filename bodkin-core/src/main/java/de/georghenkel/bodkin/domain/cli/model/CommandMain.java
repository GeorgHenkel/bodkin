package de.georghenkel.bodkin.domain.cli.model;

import java.io.File;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

public abstract class CommandMain {
  @Parameter(names = "--config", description = "Custom configuration file.",
      converter = FileConverter.class)
  private File config;
  @Parameter(names = {"--source", "-s"}, description = "File...", converter = FileConverter.class,
      required = false)
  private File sourceDiretory;
  @Parameter(names = {"--destination", "-d"},
      description = "The current folder will be generated into destination",
      converter = FileConverter.class)
  private File destinationDiretory;
  @Parameter(names = {"--verbose", "-V"}, description = "Print verbose output.")
  private boolean verboseOutput = false;
  @Parameter(names = {"--quiet", "-q"}, description = "Silence output.")
  private boolean silenceOutput = false;
  @Parameter(names = {"--help", "-h"}, description = "Show this message.", help = true)
  private boolean help;

  public File getSourceDiretory() {
    return sourceDiretory;
  }

  public void setSourceDiretory(final File sourceDiretory) {
    this.sourceDiretory = sourceDiretory;
  }

  public File getConfig() {
    return config;
  }

  public void setConfig(final File config) {
    this.config = config;
  }

  public File getDestinationDiretory() {
    return destinationDiretory;
  }

  public void setDestinationDiretory(final File destinationDiretory) {
    this.destinationDiretory = destinationDiretory;
  }

  public boolean isVerboseOutput() {
    return verboseOutput;
  }

  public void setVerboseOutput(final boolean verboseOutput) {
    this.verboseOutput = verboseOutput;
  }

  public boolean isSilenceOutput() {
    return silenceOutput;
  }

  public void setSilenceOutput(final boolean silenceOutput) {
    this.silenceOutput = silenceOutput;
  }

  public boolean isHelp() {
    return help;
  }

  public void setHelp(final boolean help) {
    this.help = help;
  }
}
