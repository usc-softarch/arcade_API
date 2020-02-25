package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.IOException;
import java.lang.String;

/**
 * Argument Handler for the path to a directory that is to contain the output
 * of an execution. To be used primarily with batch components.
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
  public boolean validateAsOutput(String value)
    throws Exception
  {
    String fs = File.separator;
    String outputDirPath = value;
    outputDirPath += fs + "arc";
    File outputDirectory = new File(outputDirPath);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    return true;
  }
  
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	
    return true;
  }
  
  //#endregion
}
