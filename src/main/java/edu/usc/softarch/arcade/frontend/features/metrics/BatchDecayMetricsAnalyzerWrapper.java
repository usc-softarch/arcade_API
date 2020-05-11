package edu.usc.softarch.arcade.frontend.features.metrics;

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
    String name = "Decay Metrics Analyzer";
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
    throws Exception
  {
    //TODO Currently hard-coded to write output to root.log.
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
