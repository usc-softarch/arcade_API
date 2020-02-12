package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import edu.usc.softarch.arcade.antipattern.detection.SmellEvolutionAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;

public class SmellEvolutionAnalyzerWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler smellsDir = SmellsDir.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return "evolutionSmellsAnalysis";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      smellsDir.getName()
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[1];
    parsedArgs[0] = smellsDir.getValue();

    SmellEvolutionAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean smellsDirValid = smellsDir.validate();

    return smellsDirValid;
  }
  //#endregion
}
