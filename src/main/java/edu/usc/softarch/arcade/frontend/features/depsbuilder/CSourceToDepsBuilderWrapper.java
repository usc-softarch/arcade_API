package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.io.IOException;
import edu.usc.softarch.arcade.facts.driver.CSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;

public class CSourceToDepsBuilderWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId()
  {
    return "cDepsBuilder";
  }

  @Override
  public String getName()
  {
    return "Dependency Builder: C";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      sourceDir.getName(),
      depsRsfFile.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
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
    parsedArgs[0] = sourceDir.getValue();
    parsedArgs[1] = depsRsfFile.getValue();

    CSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean sourceDirValid = sourceDir.validate();
    boolean depsRsfFileValid = depsRsfFile.validate();

    return (sourceDirValid && depsRsfFileValid);
  }
  //#endregion
}
