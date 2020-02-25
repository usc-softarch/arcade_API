package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import java.lang.String;
import edu.usc.softarch.arcade.antipattern.detection.SmellEvolutionAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;

public class SmellEvolutionAnalyzerWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public SmellEvolutionAnalyzerWrapper()
  {
    String id = "evolutionSmellsAnalysis";
    String name = "Smell Analysis: Evolution";
    ArgHandler[] requiredArguments =
    {
      SmellsDir.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[1];
    parsedArgs[0] = SmellsDir.getInstance().getValue();
    SmellEvolutionAnalyzer.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean smellsDirValid = SmellsDir.getInstance().validateAsInput();
    return smellsDirValid;
  }
  //#endregion
}
