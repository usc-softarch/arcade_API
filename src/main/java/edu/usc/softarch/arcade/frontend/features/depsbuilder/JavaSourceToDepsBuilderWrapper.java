package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.lang.String;
import edu.usc.softarch.arcade.facts.driver.JavaSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;

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
      SourceDir.getInstance(),
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
    parsedArgs[1] = DepsRsfFile.getInstance().getValue();
    parsedArgs[2] = BinDir.getInstance().getValue();
    JavaSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirValid = BinDir.getInstance().validateAsInput();
    boolean depsRsfFileValid = DepsRsfFile.getInstance().validateAsOutput();
    boolean sourceDirValid = SourceDir.getInstance().validateAsInput();

    return (binDirValid && depsRsfFileValid && sourceDirValid);
  }
  //#endregion
}
