package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a project directory containing multiple
 * versions of a system. The word "source" refers to the source of inputs,
 * rather than source code. This argument is primarily to be used in batch
 * components.
 *
 * @author Marcelo Schmitt Laser
 */
public class SourceDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler sourceDir;
  //#endregion

  //#region CONSTRUCTORS
  private SourceDir()
  {
    String name = "sourceDir";
    String description = "Source Directory";
    String instruction = "This is a directory containing one or more ";
    instruction += "subdirectories with different versions of the subject ";
    instruction += "system.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(sourceDir == null) sourceDir = new SourceDir();
    return sourceDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File sourceDirectory = new File(value);
    if(!sourceDirectory.exists())
      throw new Exception(value + ": Source directory not found.");
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
