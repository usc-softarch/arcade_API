package edu.usc.softarch.arcade.frontend.arghandlers;

public class BinDirPath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler binDirPath;
  //#endregion

  //#region CONSTRUCTORS
  private BinDirPath()
  {
    String name = "binDirPath";
    String description = "Binaries Directory Path";
    String instruction = "This is the path to a specific directory containing ";
    instruction += "the compiled binaries of the subject system.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(binDirPath == null) binDirPath = new BinDirPath();
    return binDirPath;
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
