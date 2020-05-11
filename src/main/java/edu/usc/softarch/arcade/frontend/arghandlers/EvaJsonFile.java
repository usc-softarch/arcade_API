package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * //TODO This whole class needs to be refactored
 *
 * @author Marcelo Schmitt Laser
 */
public class EvaJsonFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler evaJsonFile;
  //#endregion

  //#region CONSTRUCTORS
  private EvaJsonFile()
  {
	  String name = "evaJsonFile";
    String description = "EVA Json Input File";
    String instruction = "This is the path to a json file that will contain ";
    instruction += "a processed cluster file for use with EVA.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(evaJsonFile == null) evaJsonFile = new EvaJsonFile();
    return evaJsonFile;
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
