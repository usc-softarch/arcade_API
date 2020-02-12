package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.IOException;
import acdc.ACDC;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;

public class AcdcWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  private static final ArgHandler clusterFile = ClusterFile.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName() { return "acdc"; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      depsRsfFile.getName(),
      clusterFile.getName()
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = depsRsfFile.getValue();
    parsedArgs[1] = clusterFile.getValue();
    ACDC.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean depsRsfFileValid = depsRsfFile.validate();
    boolean clusterFileValid = clusterFile.validate();

    return (depsRsfFileValid && clusterFileValid);
  }
  //#endregion
}
