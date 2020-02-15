package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * Argument Handler for the path to a directory containing _smells.ser files.
 * For the path to a specific _smells.ser file, see {@link SmellsFile}.
 *
 * @author Marcelo Schmitt Laser
 */
public class SmellsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler smellsDir;
  //#endregion

  //#region CONSTRUCTORS
  private SmellsDir()
  {
    String name = "smellsDir";
    String description = "Smells SER Directory";
    String instruction = "Smells SER Directory: This is a directory containing";
    instruction += " one or more files of the form *_smells.ser.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(smellsDir == null) smellsDir = new SmellsDir();
    return smellsDir;
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
