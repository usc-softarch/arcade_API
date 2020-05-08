package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a clean Version Control System (VCS) data
 * file.
 *
 * @author Khoi
 */
public class CleanVCSDataFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler cleanVCSDataFile;
  //#endregion

  //#region CONSTRUCTORS
  private CleanVCSDataFile()
  {
    String name = "cleanVCSDataFile";
    String description = "Clean VCS Data File";
    String instruction = "This is the path to a Version Control System (VCS) ";
    instruction += "data file that has been cleaned for use in architectural ";
    instruction += "analysis.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(cleanVCSDataFile == null) cleanVCSDataFile = new CleanVCSDataFile();
    return cleanVCSDataFile;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File cleanVCSFileVar = new File(value);
    if(!cleanVCSFileVar.exists())
      throw new Exception(value + " not found.");
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
  {
    //TODO
    return true;
  }
  //#endregion
}
