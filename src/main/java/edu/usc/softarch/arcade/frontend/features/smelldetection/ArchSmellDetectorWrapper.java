package edu.usc.softarch.arcade.frontend.features.smelldetection;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.antipattern.detection.ArchSmellDetector;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class ArchSmellDetectorWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return arcade.strings.components.archSmellDetector.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.depsRsfFile.id,
      arcade.strings.args.clusterFile.id,
      arcade.strings.args.smellsFile.id
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[3];
    parsedArgs[0] = args.get(arcade.strings.args.depsRsfFile.id);
    parsedArgs[1] = args.get(arcade.strings.args.clusterFile.id);
    parsedArgs[2] = args.get(arcade.strings.args.smellsFile.id);
    ArchSmellDetector.main(parsedArgs);
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
      String errorMessage = "clusterFile not found: ";
      errorMessage += args.get(arcade.strings.args.clusterFile.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
  //#endregion
}
