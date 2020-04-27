package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * Argument Handler for the comparison distance to be used with comparison-
 * based metrics, such as A2A.
 *
 * @author Marcelo Schmitt Laser
 */
public class DistOpt
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler distOpt;
  //#endregion

  //#region CONSTRUCTORS
  private DistOpt()
  {
    String name = "distOpt";
    String description = "Comparison Distance";
    String instruction = "This is the maximum version distance between ";
    instruction += "compared versions. Default value is 1, possible ";
    instruction += "values are 1, 2 or 3.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(distOpt == null) distOpt = new DistOpt();
    return distOpt;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    Integer intValue = Integer.parseInt(value);
  	if (intValue < 1 || intValue > 3)
      throw new IOException("Invalid comparison distance, must be 1, 2, or 3.");
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
