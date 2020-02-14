package edu.usc.softarch.arcade.frontend.features.smelldetection;

import edu.usc.softarch.arcade.antipattern.detection.ArchSmellDetector;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;

public class ArchSmellDetectorWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTOR
  public ArchSmellDetectorWrapper()
  {
    String id = "archSmellDetector";
    String name = "Architectural Smell Detector";
    ArgHandler[] requiredArguments =
    {
      DepsRsfFile.getInstance(),
      ClusterFile.getInstance(),
      SmellsFile.getInstance()
    };
    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[3];
    parsedArgs[0] = DepsRsfFile.getInstance().getValue();
    parsedArgs[1] = ClusterFile.getInstance().getValue();
    parsedArgs[2] = SmellsFile.getInstance().getValue();
    ArchSmellDetector.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean depsRsfFileValid = DepsRsfFile.getInstance().validateAsInput();
    boolean clusterFileValid = ClusterFile.getInstance().validateAsInput();
    boolean smellsFileValid = SmellsFile.getInstance().validateAsOutput();

    return (depsRsfFileValid && clusterFileValid && smellsFileValid);
  }
  //#endregion
}
