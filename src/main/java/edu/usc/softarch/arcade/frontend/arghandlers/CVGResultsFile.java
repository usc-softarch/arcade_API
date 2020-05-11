package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a file containing the results of a CVG
 * analysis.
 *
 * @author Marcelo Schmitt Laser
 */
public class CVGResultsFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler cvgResultsFile;
  //#endregion

  //#region CONSTRUCTORS
  private CVGResultsFile()
  {
    String name = "cvgResultsFile";
    String description = "CVG Results File";
    String instruction = "This is the path to a file containing the results ";
    instruction += "of a CVG analysis.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(cvgResultsFile == null) cvgResultsFile = new CVGResultsFile();
    return cvgResultsFile;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO This is done because the CVG component is hard-coded and does not
    //     naturally output to a file.
    File cvgResultsFilePath = new File(value);
    if(!cvgResultsFilePath.getParentFile().exists()
      && !cvgResultsFilePath.getParentFile().mkdirs())
        throw new Exception("Failed to create CVG Results file: " + value);

    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    File cvgResultsFilePath = new File(value);
    if(!cvgResultsFilePath.getParentFile().exists()
      && !cvgResultsFilePath.getParentFile().mkdirs())
        throw new Exception("Failed to create CVG Results file: " + value);

    return true;
  }
  //#endregion
}
