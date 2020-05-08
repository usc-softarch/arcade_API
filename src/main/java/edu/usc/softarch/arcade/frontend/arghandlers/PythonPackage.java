package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for a python package. To be used by tool adapters that
 * target python tools.
 *
 * @author Khoi
 */
public class PythonPackage
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler pythonPackage;
  //#endregion

  //#region CONSTRUCTORS
  private PythonPackage()
  {
    String name = "pythonPackage";
    String description = "Python Package";
    String instruction = "This is the python package that is to be executed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(pythonPackage == null) pythonPackage = new PythonPackage();
    return pythonPackage;
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
    //TODO
    return true;
  }
  //#endregion
}
