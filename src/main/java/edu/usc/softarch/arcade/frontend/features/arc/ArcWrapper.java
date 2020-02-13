package edu.usc.softarch.arcade.frontend.features.arc;

import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.clustering.BatchClusteringEngine;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;

/**
 * See {@code edu.usc.softarch.arcade.clustering.BatchClusteringEngine}.
 */
public class ArcWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler binDir = BinDir.getInstance();
  private static final ArgHandler srcLanguage = SrcLanguage.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId() { return "arc"; }

  @Override
  public String getName()
  {
    return "ARC: Architectural Recovery based on Concerns";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      sourceDir.getName(),
      outputDir.getName(),
      binDir.getName(),
      srcLanguage.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
      outputDir,
      binDir,
      srcLanguage
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[3];
    if(srcLanguage.getValue().equals("c"))
    {
      parsedArgs = new String[4];
      parsedArgs[3] = srcLanguage.getValue();
    }
    parsedArgs[0] = sourceDir.getValue();
    parsedArgs[1] = outputDir.getValue() + fs + "arc";
    parsedArgs[2] = binDir.getValue();
    BatchClusteringEngine.main(parsedArgs);
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
    boolean srcLanguageValid = srcLanguage.validate();

    return (sourceDirValid && outputDirValid
      && binDirValid && srcLanguageValid);
  }
  //#endregion
}
