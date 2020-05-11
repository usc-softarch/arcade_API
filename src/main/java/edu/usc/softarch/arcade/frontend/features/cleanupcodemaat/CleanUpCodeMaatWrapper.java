package edu.usc.softarch.arcade.frontend.features.cleanupcodemaat;

import logical_coupling.cleanUpCodeMaat;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.VCSDataDir;

//TODO Code Maat clean-up component is hard-coded in ARCADE to output to the
//     same directory it got the input from.
public class CleanUpCodeMaatWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public CleanUpCodeMaatWrapper()
  {
    String id = "cleanUpCodeMaat";
    String name = "VCS File Cleaner";
    ArgHandler[] requiredArguments =
    {
    	VCSDataDir.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    String[] parsedArgs = new String[1];
    parsedArgs[0] = VCSDataDir.getInstance().getValue();
    cleanUpCodeMaat.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean codemaatDirValid = VCSDataDir.getInstance().validateAsInput();
    return (codemaatDirValid);
  }
  //#endregion
}
