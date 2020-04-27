package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to Arc's intermediary artifacts. It must
 * follow the _/arc/base format because of hard-coded issues in Arc.
 *
 * @author Marcelo Schmitt Laser
 */
public class ArcBaseDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler arcBaseDir;
  //#endregion

  //#region CONSTRUCTORS
  private ArcBaseDir()
  {
    String name = "arcBaseDir";
    String description = "ARC Output Base Directory";
    String instruction = "This is the directory where the outputs from ARC ";
    instruction += "will be placed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(arcBaseDir == null) arcBaseDir = new ArcBaseDir();
    return arcBaseDir;
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
	  File arcBaseDirFile = new File(value);
    if(!arcBaseDirFile.exists() && !arcBaseDirFile.mkdirs())
      throw new Exception("Failed to create output directory.");
    return true;
  }
  //#endregion
}
