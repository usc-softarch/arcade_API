package edu.usc.softarch.arcade.frontend.features.arc;

import java.io.File;
import java.io.IOException;
import java.lang.String;
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
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public ArcWrapper()
  {
    String id = "arc";
    String name = "ARC: Architectural Recovery based on Concerns";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      OutputDir.getInstance(),
      BinDir.getInstance(),
      SrcLanguage.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[3];
    if(SrcLanguage.getInstance().getValue().equals("c"))
    {
      parsedArgs = new String[4];
      parsedArgs[3] = SrcLanguage.getInstance().getValue();
    }
    parsedArgs[0] = SourceDir.getInstance().getValue();
    parsedArgs[1] = OutputDir.getInstance().getValue() + fs + "arc";
    parsedArgs[2] = BinDir.getInstance().getValue();
    BatchClusteringEngine.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean sourceDirValid = SourceDir.getInstance().validateAsInput(SourceDir.getInstance().getValue());
    boolean outputDirValid = OutputDir.getInstance().validateAsOutput(OutputDir.getInstance().getValue());
    boolean binDirValid = BinDir.getInstance().validateAsInput(BinDir.getInstance().getValue());
    boolean srcLanguageValid = SrcLanguage.getInstance().validateAsInput(SrcLanguage.getInstance().getValue());
    return (sourceDirValid && outputDirValid
      && binDirValid && srcLanguageValid);
  }
  //#endregion
}
