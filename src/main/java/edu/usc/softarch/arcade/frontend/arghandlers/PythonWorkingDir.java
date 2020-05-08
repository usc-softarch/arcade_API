package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a Python working directory, used to
 * execute Python tool adapters.
 *
 * @author Khoi
 */
public class PythonWorkingDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler pythonWorkingDir;
  //#endregion

  //#region CONSTRUCTORS
  private PythonWorkingDir()
  {
    String name = "pythonWorkingDir";
    String description = "Python Working Directory";
    String instruction = "This is the path to python module (-m) executable ";
    instruction += "directory, preferably the parent directory of a python ";
    instruction += "root package.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(pythonWorkingDir == null) pythonWorkingDir = new PythonWorkingDir();
    return pythonWorkingDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File pWorkingDir = new File(value);
    if(!pWorkingDir.exists() && !pWorkingDir.mkdirs())
      throw new Exception("Directory does not exist: " + value);
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
