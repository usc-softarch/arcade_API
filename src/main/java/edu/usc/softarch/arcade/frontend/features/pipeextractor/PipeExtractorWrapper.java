package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import java.lang.String;
import edu.usc.softarch.arcade.util.ldasupport.PipeExtractor;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ArcBaseDir;

/**
 * See {@code edu.usc.softarch.arcade.util.ldasupport.PipeExtractor}.
 */
public class PipeExtractorWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public PipeExtractorWrapper()
  {
    String id = "pipeExtractor";
    String name = "Pipe Extractor";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      ArcBaseDir.getInstance()
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
    parsedArgs[0] = SourceDir.getInstance().getValue();
    parsedArgs[1] = ArcBaseDir.getInstance().getValue();
    PipeExtractor.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean sourceDirValid = SourceDir.getInstance().validateAsInput(SourceDir.getInstance().getValue());
    boolean outputDirValid = ArcBaseDir.getInstance().validateAsOutput(ArcBaseDir.getInstance().getValue());
    return (sourceDirValid && outputDirValid);
  }
  //#endregion
}
