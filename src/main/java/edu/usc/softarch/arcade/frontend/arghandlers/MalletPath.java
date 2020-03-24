package edu.usc.softarch.arcade.frontend.arghandlers;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a mallet executable. This should always be
 * filled in automatically based on {@link MalletHome}.
 *
 * @author Marcelo Schmitt Laser
 */

public class MalletPath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler malletPath;
  //#endregion

  //#region CONSTRUCTORS
  private MalletPath()
  {
    String name = "malletPath";
    String description = "Mallet Path";
    String instruction = "Mallet Path: This is the path to Mallet's ";
    instruction += "executable (mallet for Linux or mallet.bat for Windows)";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(malletPath == null) malletPath = new MalletPath();
    return malletPath;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File MalletPath = new File(value);
	    if(!MalletPath.exists())
	      throw new IOException("Mallet Path directory doesn't exist.");
	    else
	    {       	
	    	if(SystemUtils.IS_OS_LINUX)
	        {
	    		if (!value.substring(value.length()-6).equals("mallet")) 
	        	{
		    	      throw new IOException("Mallet Path directory missing file mallet");  
	        	}
	        }
	        else if(SystemUtils.IS_OS_WINDOWS)
	        {
	        	if (!value.substring(value.length()-10).equals("mallet.bat")) 
	        	{
		    	      throw new IOException("Mallet Path directory missing file mallet.bat");  
	        	}
	        }
	        else
	          throw new UnsupportedOperationException("can't set mallet Path, unknown OS (only supports Windows and Linux-based.");
	    }	    
    return true;
  }
  
  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    //TODO
	
    return true;
  }
  
  //#endregion
}
