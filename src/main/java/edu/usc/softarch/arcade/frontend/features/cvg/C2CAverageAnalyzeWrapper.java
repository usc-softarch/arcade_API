package edu.usc.softarch.arcade.frontend.features.cvg;

import java.io.IOException;
import edu.usc.softarch.arcade.util.statistic.C2CAverageAnalyze;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;

public class C2CAverageAnalyzeWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public C2CAverageAnalyzeWrapper()
  {
    String id = "c2CAverageAnalyze";
    String name = "cvg: Cluster Coverage";
    ArgHandler[] requiredArguments =
    {
      StdOutRedirect.getInstance()     
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[1];
    parsedArgs[0] = StdOutRedirect.getInstance().getValue();    
    C2CAverageAnalyze.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean stdOutRedirectValid = StdOutRedirect.getInstance().validateAsInput();    
    return (stdOutRedirectValid);
  }
  //#endregion
}
