package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * Argument Handler for the path to a mallet executable. This should always be
 * filled in automatically based on {@link MalletHome}.
 *
 * @author Marcelo Schmitt Laser
 */
public class MalletPath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler malletPath;
  //#endregion

  //#region CONSTRUCTORS
  private MalletPath()
  {
    String name = "malletPath";
    String description = "Mallet Path";
    String instruction = "Mallet Path: This is the path to Mallet's ";
    instruction += "executable (mallet for Linux or mallet.bat for Windows)";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(malletPath == null) malletPath = new MalletPath();
    return malletPath;
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
