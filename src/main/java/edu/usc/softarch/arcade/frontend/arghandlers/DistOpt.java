package edu.usc.softarch.arcade.frontend.arghandlers;

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
    String instruction = "Comparison Distance: This is the version distance ";
    instruction += "between compared versions. Default value is 1, possible ";
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
  public boolean validate(String value)
    throws Exception
  {
    //TODO
    return true;
  }
  //#endregion
}
