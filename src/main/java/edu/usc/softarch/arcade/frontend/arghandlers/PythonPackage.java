package edu.usc.softarch.arcade.frontend.arghandlers;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a python working directory. This should always be
 * filled in automatically based on {@link }.
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
    String instruction = "Python Package: This is the path to python module,";
    instruction += "(something like root.parentDir.pythonFile)";

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
	  if (value.substring(value.length()-3).equals(".py"))
		throw new IOException("python executable package shouldn't have .py extension at the end, please remove '.py'");    		

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
