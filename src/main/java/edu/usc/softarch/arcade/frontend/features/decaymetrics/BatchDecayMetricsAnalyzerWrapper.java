package edu.usc.softarch.arcade.frontend.features.decaymetrics;

import java.io.IOException;
import java.lang.String;
import edu.usc.softarch.arcade.decay.BatchDecayMetricsAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;

public class BatchDecayMetricsAnalyzerWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public BatchDecayMetricsAnalyzerWrapper()
  {
    String id = "decayMetrics";
    String name = "Metrics: Decay";
    ArgHandler[] requiredArguments =
    {
      ClusterDir.getInstance(),
      DepsDir.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = ClusterDir.getInstance().getValue();
    parsedArgs[1] = DepsDir.getInstance().getValue();
    BatchDecayMetricsAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    boolean depsDirValid = DepsDir.getInstance().validateAsInput();
    return (clusterDirValid && depsDirValid);
  }
  //#endregion
}
