package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to an infer.mallet file. This file is
 * generated by executing the Inferencer component.
 *
 * @author Marcelo Schmitt Laser
 * @see edu.usc.softarch.arcade.frontend.tooladapters.mallet.Inferencer
 */
public class InferenceFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler inferenceFile;
  //#endregion

  //#region CONSTRUCTORS
  private InferenceFile()
  {
    String name = "inferenceFile";
    String description = "Inference File";
    String instruction = "This is the path to an infer.mallet file that is ";
    instruction += "generated by mallet.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(inferenceFile == null) inferenceFile = new InferenceFile();
    return inferenceFile;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File inferFile = new File(value);
	  if(!inferFile.exists())
	  {
      String errorMessage = "Inference file not found at path: ";
      errorMessage += value;
	    throw new Exception(errorMessage);
	  }
	  return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    File inferFile = new File(value);
    if(!inferFile.getParentFile().exists()
      && !inferFile.getParentFile().mkdirs())
        throw new Exception("Failed to create output directory.");
    return true;
  }
  //#endregion
}
