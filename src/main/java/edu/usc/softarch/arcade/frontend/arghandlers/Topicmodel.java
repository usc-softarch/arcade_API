package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a topicmodel.data file. This file is
 * generated by mallet through the TopicModelGenerator component, for use with
 * ARC.
 *
 * @author Marcelo Schmitt Laser
 * @see edu.usc.softarch.arcade.frontend.tooladapters.mallet.TopicModelGenerator
 */
public class Topicmodel
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler topicmodel;
  //#endregion

  //#region CONSTRUCTORS
  private Topicmodel()
  {
    String name = "topicmodel";
    String description = "Topic Model";
    String instruction = "This is the path to a topicmodel.data file ";
    instruction += "generated by mallet.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(topicmodel == null) topicmodel = new Topicmodel();
    return topicmodel;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File topicModelFile = new File(value);
    if(!topicModelFile.exists())
      throw new Exception(value + " not found.");
	  return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    File arcBaseDir = new File(value);
    if(!arcBaseDir.getParentFile().exists()
      && !arcBaseDir.getParentFile().mkdirs())
        throw new Exception("Failed to create TopicModel output directory.");
    return true;
  }
  //#endregion
}
