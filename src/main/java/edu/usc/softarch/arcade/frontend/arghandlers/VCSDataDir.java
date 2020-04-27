package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a directory containing VCS data files.
 *
 * @author Khoi
 */
public class VCSDataDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler vcsDataDir;
  //#endregion

  //#region CONSTRUCTORS
  private VCSDataDir()
  {
    String name = "vcsDataDir";
    String description = "VCS Data Directory";
    String instruction = "This is a directory containing one or more files ";
    instruction += "with Version Control System (VCS) data.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(vcsDataDir == null) vcsDataDir = new VCSDataDir();
    return vcsDataDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File vcsDataDirVar = new File(value);
    if(!vcsDataDirVar.exists())
    {
      String errorMessage = "VCS Data Directory not found at given path: ";
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
