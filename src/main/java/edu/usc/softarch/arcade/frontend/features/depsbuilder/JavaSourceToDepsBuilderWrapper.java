package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.io.IOException;
import edu.usc.softarch.arcade.facts.driver.JavaSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;

public class JavaSourceToDepsBuilderWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  private static final ArgHandler binDirPath = BinDirPath.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId()
  {
    return "javaDepsBuilder";
  }

  @Override
  public String getName()
  {
    return "Dependency Builder: Java";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      binDirPath.getName(),
      depsRsfFile.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      binDirPath,
      depsRsfFile
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[2];
    parsedArgs[0] = binDirPath.getValue();
    parsedArgs[1] = depsRsfFile.getValue();
    JavaSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean binDirPathValid = binDirPath.validate();
    boolean depsRsfFileValid = depsRsfFile.validate();

    return (binDirPathValid && depsRsfFileValid);
  }
  //#endregion
}
