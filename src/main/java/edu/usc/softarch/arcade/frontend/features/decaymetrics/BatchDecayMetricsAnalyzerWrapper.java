package edu.usc.softarch.arcade.frontend.features.decaymetrics;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.decay.BatchDecayMetricsAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class BatchDecayMetricsAnalyzerWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName() { return arcade.strings.components.decayMetrics.id; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.clusterDir.id,
      arcade.strings.args.depsDir.id
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[2];
    parsedArgs[0] = args.get(arcade.strings.args.clusterDir.id);
    parsedArgs[1] = args.get(arcade.strings.args.depsDir.id);

    BatchDecayMetricsAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws IllegalArgumentException, IOException
  {
    // Check whether cluster directory exists
    File clusterDir = new File(args.get(arcade.strings.args.clusterDir.id));
    if(!clusterDir.exists())
    {
      String errorMessage = "Cluster directory not found: ";
      errorMessage += args.get(arcade.strings.args.clusterDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether dependencies directory exists
    File depsDir = new File(args.get(arcade.strings.args.depsDir.id));
    if(!depsDir.exists())
    {
      String errorMessage = "Dependencies directory does not exist: ";
      errorMessage += args.get(arcade.strings.args.depsDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
  //#endregion
}
