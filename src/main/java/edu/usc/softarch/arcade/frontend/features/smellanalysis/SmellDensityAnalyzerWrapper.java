package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import java.io.PrintStream;
import java.io.File;

import edu.usc.softarch.arcade.antipattern.detection.SmellDensityAnalyzer;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;

public class SmellDensityAnalyzerWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public SmellDensityAnalyzerWrapper()
  {
    String id = "densitySmellAnalysis";
    String name = "Smell Analysis: Density";
    ArgHandler[] requiredArguments =
    {
      SmellsDir.getInstance(),
      ClusterDir.getInstance(),
      StdOutRedirect.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = SmellsDir.getInstance().getValue();
    parsedArgs[1] = ClusterDir.getInstance().getValue();

    PrintStream out = System.out;
    System.setOut(new PrintStream(new File(
      StdOutRedirect.getInstance().getValue())));

    SmellDensityAnalyzer.main(parsedArgs);

    System.setOut(out);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    boolean smellsDirValid = SmellsDir.getInstance().validateAsInput();
    boolean stdOutValid = StdOutRedirect.getInstance().validateAsOutput();
    return (clusterDirValid && smellsDirValid && stdOutValid);
  }
  //#endregion
}
