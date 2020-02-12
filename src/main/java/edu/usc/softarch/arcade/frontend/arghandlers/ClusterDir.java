package edu.usc.softarch.arcade.frontend.arghandlers;

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
    String description = "Cluster RSF Directory";
    String instruction = "Cluster RSF Directory: This is a directory ";
    instruction += "containing one or more files of the form *_cluster.rsf.";

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
  public boolean validate(String value)
    throws Exception
  {
    //TODO
    return true;
  }
  //#endregion
}
