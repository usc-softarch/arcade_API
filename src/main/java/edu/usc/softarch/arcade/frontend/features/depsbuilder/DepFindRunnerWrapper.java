package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import edu.usc.softarch.arcade.antipattern.detection.interfacebased.DepFindRunner;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;

public class DepFindRunnerWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public DepFindRunnerWrapper()
  {
    String id = "depFindRunner";
    String name = "Dependency Finder for Advanced Smell Detection";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      OutputDir.getInstance(),
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
    parsedArgs[0] = SourceDir.getInstance().getValue();
    parsedArgs[1] = OutputDir.getInstance().getValue();
    parsedArgs[2] = BinDir.getInstance().getValue();
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
    boolean outputDirValid = OutputDir.getInstance().validateAsOutput();

    return (binDirValid && sourceDirPathValid && outputDirValid);
  }
  //#endregion
}
