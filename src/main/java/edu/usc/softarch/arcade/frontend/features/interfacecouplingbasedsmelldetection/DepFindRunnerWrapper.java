package edu.usc.softarch.arcade.frontend.features.interfacecouplingbasedsmelldetection;

import edu.usc.softarch.arcade.antipattern.detection.interfacebased.DepFindRunner;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepFinderDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;

public class DepFindRunnerWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public DepFindRunnerWrapper()
  {
    String id = "depFindRunner";
    String name = "Dep Find Runner";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      DepFinderDir.getInstance(),
      BinDir.getInstance()
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
    parsedArgs[0] = BinDir.getInstance().getValue();
    parsedArgs[1] = DepFinderDir.getInstance().getValue();
    parsedArgs[2] = SourceDir.getInstance().getValue();
    DepFindRunner.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirValid = BinDir.getInstance().validateAsInput();
    boolean sourceDirPathValid = SourceDir.getInstance().validateAsInput();
    boolean depFinderDirValid = DepFinderDir.getInstance().validateAsOutput();

    return (binDirValid && sourceDirPathValid && depFinderDirValid);
  }
  //#endregion
}