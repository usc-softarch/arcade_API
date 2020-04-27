package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * Argument Handler for the name of a directory containing compiled binaries
 * from a project. Not to be confused with {@link BinDirPath}.
 *
 * @author Marcelo Schmitt Laser
 */
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
    String description = "Binaries Directory Name";
    String instruction = "This is the name of the directory containing the ";
    instruction += "compiled binaries for a subject system.";

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
