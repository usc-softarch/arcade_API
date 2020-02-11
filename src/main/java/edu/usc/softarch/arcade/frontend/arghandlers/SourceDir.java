package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

public class SourceDir
  implements ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler sourceDir;
  private String value;
  //#endregion

  //#region CONSTRUCTORS
  private SourceDir() { }

  public static ArgHandler getInstance()
  {
    if(sourceDir == null) sourceDir = new SourceDir();
    return sourceDir;
  }
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName() { return "sourceDir"; }
  @Override
  public String getValue() { return value; }

  @Override
  public String setValue(String value)
    throws IllegalArgumentException
  {
    try { validate(value); }
    catch(Exception e)
    {
      throw new IllegalArgumentException(e);
    }

    String oldValue = this.value;
    this.value = value;
    return oldValue;
  }
  //#endregion

  //#region PRESENTATION
  @Override
  public String getDescription() { return "Source Directory"; }
  @Override
  public String getInstruction()
  {
    String instruction = "Source Directory: This is a directory containing one";
    instruction += " or more subdirectories with different versions of the ";
    instruction += "subject system.";
    return instruction;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validate(String value)
    throws Exception
  {
    File sourceDirectory = new File(value);
    if(!sourceDirectory.exists())
    {
      String errorMessage = "Source directory not found: ";
      errorMessage += value;
      throw new IllegalArgumentException(errorMessage);
    }
    return true;
  }
  //#endregion
}
