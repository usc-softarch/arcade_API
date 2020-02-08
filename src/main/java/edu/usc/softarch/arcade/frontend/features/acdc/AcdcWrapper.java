package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import acdc.ACDC;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class AcdcWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName() { return arcade.strings.components.acdc.id; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.depsRsfFile.id,
      arcade.strings.args.clusterFile.id
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
    parsedArgs[0] = args.get(arcade.strings.args.depsRsfFile.id);
    parsedArgs[1] = args.get(arcade.strings.args.clusterFile.id);
    ACDC.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws IllegalArgumentException, IOException
  {
    // Check whether dependency file exists
    File depsRsfFile = new File(args.get(arcade.strings.args.depsRsfFile.id));
    if(!depsRsfFile.exists())
    {
      String errorMessage = "depsRsfFile not found: ";
      errorMessage += args.get(arcade.strings.args.depsRsfFile.id);
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether cluster file exists
    File clusterFile = new File(args.get(arcade.strings.args.clusterFile.id));
    if(!clusterFile.exists())
    {
      String errorMessage = "clusterFile directory does not exist: ";
      errorMessage += args.get(arcade.strings.args.clusterFile.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
  //#endregion
}
