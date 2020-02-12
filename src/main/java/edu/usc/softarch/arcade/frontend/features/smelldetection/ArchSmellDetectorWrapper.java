package edu.usc.softarch.arcade.frontend.features.smelldetection;

import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.antipattern.detection.ArchSmellDetector;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;

public class ArchSmellDetectorWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  private static final ArgHandler clusterFile = ClusterFile.getInstance();
  private static final ArgHandler smellsFile = SmellsFile.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return "archSmellDetector";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      depsRsfFile.getName(),
      clusterFile.getName(),
      smellsFile.getName()
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[3];
    parsedArgs[0] = args.get(depsRsfFile.getName());
    parsedArgs[1] = args.get(clusterFile.getName());
    parsedArgs[2] = args.get(smellsFile.getName());
    ArchSmellDetector.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws Exception
  {
    boolean depsRsfFileValid = depsRsfFile.validate(args);
    boolean clusterFileValid = clusterFile.validate(args);
    boolean smellsFileValid = smellsFile.validate(args);

    return (depsRsfFileValid && clusterFileValid && smellsFileValid);
  }
  //#endregion
}
