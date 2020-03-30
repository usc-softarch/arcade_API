package edu.usc.softarch.arcade.frontend.features.cleanupcodemaat;

import java.io.IOException;
import logical_coupling.cleanUpCodeMaat;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.CodemaatDir;

public class CleanUpCodeMaatWrapper
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public CleanUpCodeMaatWrapper()
  {
    String id = "cleanUpCodeMaat";
    String name = "CleanUpCodeMaat: Clean Up for Code-Maat generated csv files (not neccessary in C/C++).";
    ArgHandler[] requiredArguments =
    {
    	CodemaatDir.getInstance()     
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    String[] parsedArgs = new String[1];
    parsedArgs[0] = CodemaatDir.getInstance().getValue();    
    cleanUpCodeMaat.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean codemaatDirValid = CodemaatDir.getInstance().validateAsInput();    
    return (codemaatDirValid);
  }
  //#endregion
}
