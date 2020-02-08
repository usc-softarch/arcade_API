package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.antipattern.detection.SmellDensityAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class SmellDensityAnalyzerWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return arcade.strings.components.smellAnalysis.density.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.smellsDir.id,
      arcade.strings.args.clusterDir.id,
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
    parsedArgs[0] = args.get(arcade.strings.args.smellsDir.id);
    parsedArgs[1] = args.get(arcade.strings.args.clusterDir.id);

    SmellDensityAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws IllegalArgumentException, IOException
  {
    // Check whether smells directory exists
    File smellsDir = new File(args.get(arcade.strings.args.smellsDir.id));
    if(!smellsDir.exists())
    {
      String errorMessage = "Smells directory not found: ";
      errorMessage += args.get(arcade.strings.args.smellsDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether cluster directory exists
    File clusterDir = new File(args.get(arcade.strings.args.clusterDir.id));
    if(!clusterDir.exists())
    {
      String errorMessage = "Cluster directory not found: ";
      errorMessage += args.get(arcade.strings.args.clusterDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
  //#endregion
}
