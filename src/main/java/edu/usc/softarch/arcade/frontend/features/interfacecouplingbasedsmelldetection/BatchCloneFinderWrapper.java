package edu.usc.softarch.arcade.frontend.features.interfacecouplingbasedsmelldetection;

import edu.usc.softarch.arcade.antipattern.detection.interfacebased.BatchCloneFinder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.CloneDir;

public class BatchCloneFinderWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public BatchCloneFinderWrapper()
  {
    String id = "batchCloneFinder";
    String name = "Batch Clone Finder";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      CloneDir.getInstance()      
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
    parsedArgs[1] = CloneDir.getInstance().getValue();    
    BatchCloneFinder.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean sourceDirPathValid = SourceDir.getInstance().validateAsInput();
    boolean cloneDirValid = CloneDir.getInstance().validateAsOutput();

    return (sourceDirPathValid && cloneDirValid);
  }
  //#endregion
}