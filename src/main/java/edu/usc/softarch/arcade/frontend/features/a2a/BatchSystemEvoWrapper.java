package edu.usc.softarch.arcade.frontend.features.a2a;

import java.io.IOException;
import edu.usc.softarch.arcade.metrics.BatchSystemEvo;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;

public class BatchSystemEvoWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler distOpt = DistOpt.getInstance();
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId()
  {
    return "a2a";
  }

  @Override
  public String getName()
  {
    return "Metrics: A2A";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      distOpt.getName(),
      sourceDir.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      distOpt,
      sourceDir
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[3];
    parsedArgs[0] = "-distopt";
    parsedArgs[1] = distOpt.getValue();
    parsedArgs[2] = sourceDir.getValue();

    BatchSystemEvo.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean distOptValid = distOpt.validate();
    boolean sourceDirValid = sourceDir.validate();

    return (distOptValid && sourceDirValid);
  }
  //#endregion
}
