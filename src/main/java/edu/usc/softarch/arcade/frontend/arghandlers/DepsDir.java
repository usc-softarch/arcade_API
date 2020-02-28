package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a directory containing _deps.rsf files.
 * For the path to a specific _deps.rsf file, see {@link DepsRsfFile}.
 */

public class DepsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler depsDir;
  //#endregion

  //#region CONSTRUCTORS
  private DepsDir()
  {
    String name = "depsDir";
    String description = "Dependencies RSF Directory";
    String instruction = "Dependencies RSF Directory: This is a directory ";
    instruction += "containing one or more files of the form *_deps.rsf.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(depsDir == null) depsDir = new DepsDir();
    return depsDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File depsDirectory = new File(value);
	    if(!depsDirectory.exists() && !depsDirectory.mkdirs())
	      throw new IOException("deps directory doesn't exist.");
	    else
	    {    	
	    	File[] files = depsDirectory.listFiles();
	        int dirLength = depsDirectory.list().length;
	        boolean depsFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if ((filename.length() >= 4) && (filename.substring(filename.length()-4).equals(".rsf")))
				{
					depsFileContain = true;
					break;
				}
	    	}
	    	if(!depsFileContain)
	    	      throw new IOException("deps directory doesn't have .rsf file.");    		
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
