package edu.usc.softarch.arcade.frontend.features.metrics;

import java.io.PrintStream;
import java.io.File;

import edu.usc.softarch.arcade.metrics.BatchSystemEvo;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.A2AResult;

public class BatchSystemEvoWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public BatchSystemEvoWrapper()
  {
    String id = "a2a";
    String name = "Architecture to Architecture: Pairwise";
    ArgHandler[] requiredArguments =
    {
      ClusterDir.getInstance(),
      A2AResult.getInstance(),
      DistOpt.getInstance() //TODO Make this optional
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
    parsedArgs[0] = "-distopt";
    parsedArgs[1] = DistOpt.getInstance().getValue();
    parsedArgs[2] = ClusterDir.getInstance().getValue();

    PrintStream out = System.out;
    System.setOut(new PrintStream(
      new File(A2AResult.getInstance().getValue())));

    BatchSystemEvo.main(parsedArgs);

    System.setOut(out);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean distOptValid = DistOpt.getInstance().validateAsInput();
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    boolean a2aResultValid = A2AResult.getInstance().validateAsOutput();
    return (distOptValid && clusterDirValid && a2aResultValid);
  }
  //#endregion
}
