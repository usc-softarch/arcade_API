package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.IOException;

/**
 * Argument Handler for the path to Arc's intermediary artifacts. It must
 * follow the specific _/arc/base format because of hard-coded issues in Arc.
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
    String instruction = "This is the directory where the inputs for ARC will ";
    instruction += "be placed.";

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
    File arcBaseDir = new File(value);
    if(!arcBaseDir.exists() && !arcBaseDir.mkdirs())
      throw new IOException("Failed to create output directory.");

    return true;
  }
  //#endregion
}
