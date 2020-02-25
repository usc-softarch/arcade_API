package edu.usc.softarch.arcade.frontend.arghandlers;

import java.lang.String;
import java.io.IOException;

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
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	if (!(value.equals("1")) 
		&& !(value.equals("2"))
			&& !(value.equals("3")))
       throw new IOException("Invalid input, must be 1, 2, or 3.");
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
