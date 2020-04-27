package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to the Code Maat executable jar file.
 *
 * @author Khoi
 */
public class CodeMaatPath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler codeMaatPath;
  //#endregion

  //#region CONSTRUCTORS
  private CodeMaatPath()
  {
    String name = "codeMaatPath";
    String description = "Code Maat Path";
    String instruction = "This is the path to the Code Maat executable jar ";
    instruction += "file.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(codeMaatPath == null) codeMaatPath = new CodeMaatPath();
    return codeMaatPath;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File codeMaatPathVar = new File(value);
    if(!codeMaatPathVar.exists())
    {
      String errorMessage = "Code Maat executable jar not found at path: ";
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
