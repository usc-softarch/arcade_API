package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a directory that is to contain the output
 * of an execution. To be used mainly with workflow components.
 *
 * @author Marcelo Schmitt Laser
 */

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
    String instruction = "This is the directory where all output files will ";
    instruction += "go. Necessary subdirectories will be created if not ";
    instruction += "present.";

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
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File outputDirectory = new File(value);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new Exception("Failed to create output directory.");
    return true;
  }
  //#endregion
}
