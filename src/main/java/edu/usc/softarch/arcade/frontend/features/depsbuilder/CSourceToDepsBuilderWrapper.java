package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import edu.usc.softarch.arcade.facts.driver.CSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;

public class CSourceToDepsBuilderWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public CSourceToDepsBuilderWrapper()
  {
    String id = "cDepsBuilder";
    String name = "Dependency Builder: C";
    ArgHandler[] requiredArguments =
    {
      DepsRsfFile.getInstance(),
      BinDirPath.getInstance()
    };
    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = BinDirPath.getInstance().getValue();
    parsedArgs[1] = DepsRsfFile.getInstance().getValue();
    CSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirPathValid = BinDirPath.getInstance().validateAsInput();
    boolean depsRsfFileValid = DepsRsfFile.getInstance().validateAsOutput();

    return (binDirPathValid && depsRsfFileValid);
  }
  //#endregion
}
