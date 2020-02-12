package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import java.io.IOException;
import edu.usc.softarch.arcade.antipattern.detection.SmellDensityAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

public class SmellDensityAnalyzerWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler smellsDir = SmellsDir.getInstance();
  private static final ArgHandler clusterDir = ClusterDir.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return "densitySmellAnalysis";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      smellsDir.getName(),
      clusterDir.getName()
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = smellsDir.getValue();
    parsedArgs[1] = clusterDir.getValue();

    SmellDensityAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean clusterDirValid = clusterDir.validate();
    boolean smellsDirValid = smellsDir.validate();

    return (clusterDirValid && smellsDirValid);
  }
  //#endregion
}
