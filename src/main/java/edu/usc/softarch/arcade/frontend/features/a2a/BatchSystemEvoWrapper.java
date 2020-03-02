  package edu.usc.softarch.arcade.frontend.features.a2a;

import edu.usc.softarch.arcade.metrics.BatchSystemEvo;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

public class BatchSystemEvoWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public BatchSystemEvoWrapper()
  {
    String id = "a2a";
    String name = "Architecture to Architecture Pairwise Compute";
    ArgHandler[] requiredArguments =
    {
      ClusterDir.getInstance(),
      DistOpt.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[3];
    parsedArgs[1] = "-distopt";
    parsedArgs[2] = DistOpt.getInstance().getValue();
    parsedArgs[0] = ClusterDir.getInstance().getValue();        
    BatchSystemEvo.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean distOptValid = DistOpt.getInstance().validateAsInput();
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    return (distOptValid && clusterDirValid);
  }
  //#endregion
}
