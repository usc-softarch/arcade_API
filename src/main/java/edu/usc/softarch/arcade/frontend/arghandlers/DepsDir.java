package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a dependencies data directory.
 *
 * @author Marcelo Schmitt Laser
 */
public class DepsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler depsDir;
  //#endregion

  //#region CONSTRUCTORS
  private DepsDir()
  {
    String name = "depsDir";
    String description = "Dependencies Directory";
    String instruction = "Dependencies Directory: This is a directory ";
    instruction += "containing one or more files with dependency data from ";
    instruction += "the subject system."

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(depsDir == null) depsDir = new DepsDir();
    return depsDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File depsDirectory = new File(value);
    if(!depsDirectory.exists())
    {
      String errorMessage = "Dependencies directory not found at given path: ";
      errorMessage += value;
      throw new Exception(errorMessage);
    }
	  return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File depsDirectory = new File(value);
    if(!depsDirectory.exists() && !depsDirectory.mkdirs())
      throw new IOException("Failed to create dependencies directory.");
    return true;
  }
  //#endregion
}
