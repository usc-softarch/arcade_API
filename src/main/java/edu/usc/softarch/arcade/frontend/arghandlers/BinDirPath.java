package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the exact path of a directory with the compiled
 * binaries of a system. Not to be confused with {@link BinDir}.
 *
 * @author Marcelo Schmitt Laser
 */
public class BinDirPath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler binDirPath;
  //#endregion

  //#region CONSTRUCTORS
  private BinDirPath()
  {
    String name = "binDirPath";
    String description = "Binaries Directory Path";
    String instruction = "This is the path to the directory containing ";
    instruction += "the compiled binaries for a subject system.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(binDirPath == null) binDirPath = new BinDirPath();
    return binDirPath;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File binDirectory = new File(value);
    if(!binDirectory.exists())
    {
      String errorMessage = "Binaries Directory not found at given path: ";
      errorMessage += value;
      throw new Exception(errorMessage);
    }
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    //TODO
    return true;
  }
  //#endregion
}
