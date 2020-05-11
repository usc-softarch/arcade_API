package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the exact path of a file to which to write the results
 * of an A2A analysis.
 *
 * @author Marcelo Schmitt Laser
 */
public class A2AResult
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler a2aResult;
  //#endregion

  //#region CONSTRUCTORS
  private A2AResult()
  {
    String name = "a2aResult";
    String description = "A2A Result File";
    String instruction = "This is the path to a file to which to write the ";
    instruction += "results of an A2A analysis.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(a2aResult == null) a2aResult = new A2AResult();
    return a2aResult;
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
    File a2aResultFile = new File(value);
    if (!a2aResultFile.getParentFile().exists()
      && !a2aResultFile.getParentFile().mkdirs())
        throw new Exception("Failed to create A2A file output directory.");
    return true;
  }
  //#endregion
}
