package edu.usc.softarch.arcade.frontend.features.smelldetection;

import java.io.IOException;
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
  public String getId()
  {
    return "archSmellDetector";
  }

  @Override
  public String getName()
  {
    return "Architectural Smell Detector";
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

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      depsRsfFile,
      clusterFile,
      smellsFile
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[3];
    parsedArgs[0] = depsRsfFile.getValue();
    parsedArgs[1] = clusterFile.getValue();
    parsedArgs[2] = smellsFile.getValue();
    ArchSmellDetector.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean depsRsfFileValid = depsRsfFile.validate();
    boolean clusterFileValid = clusterFile.validate();
    boolean smellsFileValid = smellsFile.validate();

    return (depsRsfFileValid && clusterFileValid && smellsFileValid);
  }
  //#endregion
}
