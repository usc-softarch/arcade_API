package edu.usc.softarch.arcade.frontend.arghandlers;

public class BinDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler binDir;
  //#endregion

  //#region CONSTRUCTORS
  private BinDir()
  {
    String name = "binDir";
    String description = "Classes Directory";
    String instruction = "Classes Directory: This is the directory in each ";
    instruction += "subject system that contains the compiled classes of that ";
    instruction += "system version.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(binDir == null) binDir = new BinDir();
    return binDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validate(String value)
    throws Exception
  {
    //TODO
    return true;
  }
  //#endregion
}
