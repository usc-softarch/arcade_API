package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.IOException;

public class OutputDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler outputDir;
  //#endregion

  //#region CONSTRUCTORS
  private OutputDir()
  {
    String name = "outputDir";
    String description = "Output Directory";
    String instruction = "Output Directory: This is the directory where all ";
    instruction += "output files go. Necessary subdirectories will be created ";
    instruction += "if not present.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(outputDir == null) outputDir = new OutputDir();
    return outputDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validate(String value)
    throws Exception
  {
    File outputDirectory = new File(value);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    return true;
  }
  //#endregion
}
