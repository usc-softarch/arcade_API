package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a directory containing _smells.ser files.
 * For the path to a specific _smells.ser file, see {@link SmellsFile}.
 *
 * @author Marcelo Schmitt Laser
 */

public class SmellsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler smellsDir;
  //#endregion

  //#region CONSTRUCTORS
  private SmellsDir()
  {
    String name = "smellsDir";
    String description = "Smells SER Directory";
    String instruction = "This is a directory containing one or more files ";
    instruction += "of the form *_smells.ser.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(smellsDir == null) smellsDir = new SmellsDir();
    return smellsDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File smellDirectory = new File(value);
    if (!smellDirectory.exists())
      throw new Exception("Smells SER directory doesn't exist.");
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File smellDirectory = new File(value);
    if(!smellDirectory.exists() && !smellDirectory.mkdirs())
      throw new Exception("Failed to create Smells SER directory.");
    return true;
  }
  //#endregion
}
