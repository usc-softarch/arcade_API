package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a file containing the logs of a Git project.
 *
 * @author Khoi
 */
public class GitProjectLog
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler gitProjectLog;
  //#endregion

  //#region CONSTRUCTORS
  private GitProjectLog()
  {
    String name = "gitProjectLog";
    String description = "Git Project Log";
    String instruction = "This is a file containing the log of a Git project.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(gitProjectLog == null) gitProjectLog = new GitProjectLog();
    return gitProjectLog;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File gitProjectLogFile = new File(value);
    if(!gitProjectLogFile.exists())
      throw new Exception("Git log file not found at path: " + value);
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
