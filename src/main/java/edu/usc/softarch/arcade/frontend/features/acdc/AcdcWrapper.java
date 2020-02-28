package edu.usc.softarch.arcade.frontend.features.acdc;

import acdc.ACDC;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;

public class AcdcWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public AcdcWrapper()
  {
    String id = "acdcWrapper";
    String name = "ACDC: Algorithm for Comprehension-Driven Clustering";
    ArgHandler[] requiredArguments =
    {
      DepsRsfFile.getInstance(),
      ClusterFile.getInstance()
    };
    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = DepsRsfFile.getInstance().getValue();
    parsedArgs[1] = ClusterFile.getInstance().getValue();
    ACDC.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean depsRsfFileValid = DepsRsfFile.getInstance().validateAsInput();
    boolean clusterFileValid = ClusterFile.getInstance().validateAsOutput();

    return (depsRsfFileValid && clusterFileValid);
  }
  //#endregion
}
