package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a Clones directory, which contains data
 * on clones detected in the subject system.
 *
 * @author Khoi
 */
public class CloneDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler cloneDir;
  //#endregion

  //#region CONSTRUCTORS
  private CloneDir()
  {
    String name = "cloneDir";
    String description = "Clones Directory";
    String instruction = "This is the path to a directory containing ";
    instruction += "clone-related data.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(cloneDir == null) cloneDir = new CloneDir();
    return cloneDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File cloneDirectory = new File(value);
    if(!cloneDirectory.exists())
      throw new Exception("Clone Directory not found at given path: " + value);
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File cloneDirectory = new File(value);
    if(!cloneDirectory.exists() && !cloneDirectory.mkdirs())
      throw new Exception("Failed to create clones directory.");
    return true;
  }
  //#endregion
}
