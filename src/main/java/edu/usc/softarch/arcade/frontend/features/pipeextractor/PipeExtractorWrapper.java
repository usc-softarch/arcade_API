package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import java.io.File;
import edu.usc.softarch.arcade.util.ldasupport.PipeExtractor;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ArcBaseDir;

/**
 * See {@code edu.usc.softarch.arcade.util.ldasupport.PipeExtractor}.
 */
public class PipeExtractorWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler arcBaseDir = ArcBaseDir.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId() { return "pipeExtractor"; }

  @Override
  public String getName() { return "Pipe Extractor"; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      sourceDir.getName(),
      arcBaseDir.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
      arcBaseDir
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String fs = File.separator;
    String[] parsedArgs = new String[2];
    parsedArgs[0] = sourceDir.getValue();
    parsedArgs[1] = arcBaseDir.getValue();
    parsedArgs[1] += fs + "arc" + fs + "base";
    PipeExtractor.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean sourceDirValid = sourceDir.validate();
    boolean outputDirValid = arcBaseDir.validate();

    return (sourceDirValid && outputDirValid);
  }
  //#endregion
}
