package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.antipattern.detection.SmellEvolutionAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class SmellEvolutionAnalyzerWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return arcade.strings.components.smellAnalysis.evolution.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.smellsDir.id
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[1];
    parsedArgs[0] = args.get(arcade.strings.args.smellsDir.id);

    SmellEvolutionAnalyzer.main(parsedArgs);
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
      String errorMessage = "Smells Directory not found: ";
      errorMessage += args.get(arcade.strings.args.smellsDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
  //#endregion
}
