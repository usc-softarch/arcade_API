package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.IOException;

/**
 * Argument Handler for the path to a depFinderRunner output directory
 * To be used primarily with interface and coupling-based smell detection components.
 *
 * @author Khoi
 */

public class CloneDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler cloneDir;
  //#endregion

  //#region CONSTRUCTORS
  private CloneDir()
  {
    String name = "cloneDir";
    String description = "Clone Directory";
    String instruction = "Clone Directory: This is the directory where all ";
    instruction += "BatchCloneFinder output files go, usually as _clone.xml format ";
    
    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(cloneDir == null) cloneDir = new CloneDir();
    return cloneDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File cloneDirectory = new File(value);
	    if(!cloneDirectory.exists() && !cloneDirectory.mkdirs())
	      throw new IOException("Failed to create output directory.");

    return true;
  }
  
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File cloneDirectory = new File(value);
	    if(!cloneDirectory.exists())
	      throw new IOException("clone directory doesn't exist." + value);
	    else
	    {    	
	    	File[] files = cloneDirectory.listFiles();
	        int dirLength = cloneDirectory.list().length;
	        boolean cloneFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if ((filename.length() >= 10) && (filename.substring(filename.length()-10).equals("_clone.xml")))
				{
					cloneFileContain = true;
					break;
				}				 
	    	}
	    	if(!cloneFileContain)
	    	      throw new IOException("clone directory doesn't have _clone.xml file.");    
	    }
    return true;
  }
  
  //#endregion
}