package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.lang.String;
import edu.usc.softarch.arcade.facts.driver.JavaSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;

public class JavaSourceToDepsBuilderWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public JavaSourceToDepsBuilderWrapper()
  {
    String id = "javaDepsBuilder";
    String name = "Dependency Builder: Java";
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
    JavaSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirPathValid = BinDirPath.getInstance().validateAsInput(BinDirPath.getInstance().getValue());
    boolean depsRsfFileValid = DepsRsfFile.getInstance().validateAsOutput(DepsRsfFile.getInstance().getValue());

    return (binDirPathValid && depsRsfFileValid);
  }
  //#endregion
}
