package edu.usc.softarch.arcade.frontend.features.decaymetrics;

import java.io.IOException;
import edu.usc.softarch.arcade.decay.BatchDecayMetricsAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;

public class BatchDecayMetricsAnalyzerWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler clusterDir = ClusterDir.getInstance();
  private static final ArgHandler depsDir = DepsDir.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId() { return "decayMetrics"; }

  @Override
  public String getName() { return "Metrics: Decay"; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      clusterDir.getName(),
      depsDir.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      clusterDir,
      depsDir
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = clusterDir.getValue();
    parsedArgs[1] = depsDir.getValue();

    BatchDecayMetricsAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean clusterDirValid = clusterDir.validate();
    boolean depsDirValid = depsDir.validate();

    return (clusterDirValid && depsDirValid);
  }
  //#endregion
}
