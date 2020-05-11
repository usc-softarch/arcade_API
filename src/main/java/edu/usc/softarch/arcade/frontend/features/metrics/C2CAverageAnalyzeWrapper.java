package edu.usc.softarch.arcade.frontend.features.metrics;

import java.io.PrintStream;
import java.io.File;

import edu.usc.softarch.arcade.util.statistic.C2CAverageAnalyze;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.CVGResultsFile;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;

public class C2CAverageAnalyzeWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public C2CAverageAnalyzeWrapper()
  {
    String id = "c2cAverageAnalyze";
    String name = "CVG: C2C Averages Analyze";
    ArgHandler[] requiredArguments =
    {
      CVGResultsFile.getInstance(),
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
    //TODO This sometimes throws an NPE due to what I'm sure is a bug
    //     in ARCADE itself. Can't be fixed until I'm working through
    //     the ARCADE source.
    String[] parsedArgs = new String[1];
    parsedArgs[0] = CVGResultsFile.getInstance().getValue();

    PrintStream out = System.out;
    System.setOut(new PrintStream(new File(
      StdOutRedirect.getInstance().getValue())));

    C2CAverageAnalyze.main(parsedArgs);

    System.setOut(out);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean cvgResultsFileValid =
      CVGResultsFile.getInstance().validateAsInput();
    boolean stdOutValid = StdOutRedirect.getInstance().validateAsOutput();
    return (cvgResultsFileValid && stdOutValid);
  }
  //#endregion
}
