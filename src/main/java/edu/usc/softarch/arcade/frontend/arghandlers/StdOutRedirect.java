package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to redirect StdOut stream to.
 *
 * @author Khoi
 */
public class StdOutRedirect
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler stdOutRedirect;
  //#endregion

  //#region CONSTRUCTORS
  private StdOutRedirect()
  {
    String name = "stdOutRedirect";
    String description = "Standard Output Redirection File";
    String instruction = "This is a path to which the StdOut stream will be ";
    instruction += "redirected.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(stdOutRedirect == null) stdOutRedirect = new StdOutRedirect();
    return stdOutRedirect;
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
    File stdOutFile = new File(value);
    if(!stdOutFile.getParentFile().exists()
      && !stdOutFile.getParentFile().mkdirs())
       throw new Exception("Failed to create StdOut file: " + value);

    return true;
  }
  //#endregion
}
