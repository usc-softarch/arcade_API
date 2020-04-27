package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to a Cluster Directory, which contains cluster
 * data obtained by an architectural recovery technique. Not to be confused with
 * {@link ClusterFile}.
 *
 * @author Khoi
 */
public class ClusterDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler clusterDir;
  //#endregion

  //#region CONSTRUCTORS
  private ClusterDir()
  {
    String name = "clusterDir";
    String description = "Cluster Directory";
    String instruction = "This is the path to a directory containing cluster ";
    instruction += "data obtained by an architectural recovery technique.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(clusterDir == null) clusterDir = new ClusterDir();
    return clusterDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File clusterDirectory = new File(value);
    if(!clusterDirectory.exists())
    {
      String errorMessage = "Cluster Directory not found at given path: ";
      errorMessage += value;
      throw new Exception(errorMessage);
    }
    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File clusterDirectory = new File(value);
    if(!clusterDirectory.exists() && !clusterDirectory.mkdirs())
      throw new Exception("Failed to create cluster directory.");
    return true;
  }
  //#endregion
}
