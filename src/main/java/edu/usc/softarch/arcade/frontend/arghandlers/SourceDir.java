package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.lang.String;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

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
    String instruction = "Source Directory: This is a directory containing one";
    instruction += " or more subdirectories with different versions of the ";
    instruction += "subject system.";

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
    {     
      throw new IllegalArgumentException(value + "Source directory not found");
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
  
  @Override
  public boolean validate(String value)
    throws Exception
  {
    //TODO
	
    return true;
  }
  //#endregion
}
