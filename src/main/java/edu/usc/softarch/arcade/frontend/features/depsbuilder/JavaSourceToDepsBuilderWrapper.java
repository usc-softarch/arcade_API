package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.facts.driver.JavaSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;

public class JavaSourceToDepsBuilderWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler binDir = BinDir.getInstance();
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
      sourceDir.getName(),
      outputDir.getName(),
      binDir.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
      outputDir,
      binDir
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[2];
    parsedArgs[0] = sourceDir.getValue();
    parsedArgs[0] += fs + binDir.getValue();

    //TODO Fix this so it's not hardcoded, i.e. infers from path
    String projectName = "Struts2";

    parsedArgs[1] = outputDir.getValue();
    parsedArgs[1] += fs + "commonRes" + fs + projectName + "_deps.rsf";
    JavaSourceToDepsBuilder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean sourceDirValid = sourceDir.validate();
    boolean outputDirValid = outputDir.validate();
    boolean binDirValid = binDir.validate();

    return (sourceDirValid && outputDirValid && binDirValid);
  }
  //#endregion
}
