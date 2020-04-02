package edu.usc.softarch.arcade.frontend.arghandlers;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to file that will serve as target for the
 * StdOut stream.
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
    String instruction = "Standard Output Redirection File: This is the file ";
    instruction += "path to which the standard output of a tool will be ";
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
	  File stdOutFile = new File(value);
    if(!stdOutFile.exists())
      throw new IOException("StdOutRedirect file does not exist.");

    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    File stdOutFile = new File(value);
	    if(!stdOutFile.getParentFile().exists()
        && !stdOutFile.getParentFile().mkdirs())
	       throw new IOException("Failed to create StdOut file.");

    return true;
  }

  //#endregion
}
