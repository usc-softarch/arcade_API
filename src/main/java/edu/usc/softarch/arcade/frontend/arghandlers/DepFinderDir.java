package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.IOException;

/**
 * Argument Handler for the path to a depFinderRunner output directory
 * To be used primarily with interface and coupling-based smell detection components.
 *
 * @author Khoi
 */

public class DepFinderDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler depFinderDir;
  //#endregion

  //#region CONSTRUCTORS
  private DepFinderDir()
  {
    String name = "depFinderDir";
    String description = "Dep Finder Directory";
    String instruction = "Dep Finder Directory: This is the directory where all ";
    instruction += "depFinderRunner output files go, usually as xml format ";    

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(depFinderDir == null) depFinderDir = new DepFinderDir();
    return depFinderDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
	  File depFinderDirectory = new File(value);
	    if(!depFinderDirectory.exists() && !depFinderDirectory.mkdirs())
	      throw new IOException("Failed to create output directory.");

    return true;
  }
  
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File depFinderDirectory = new File(value);
	    if(!depFinderDirectory.exists())
	      throw new IOException("depFinder directory doesn't exist." + value);
	    else
	    {    	
	    	File[] files = depFinderDirectory.listFiles();
	        int dirLength = depFinderDirectory.list().length;
	        boolean depFinderFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if ((filename.length() >= 9) && (filename.substring(filename.length()-9).equals("_deps.xml")))
				{
					depFinderFileContain = true;
					break;
				}				 
	    	}
	    	if(!depFinderFileContain)
	    	      throw new IOException("depFinder directory doesn't have _deps.xml file.");    
	    }
    return true;
  }
  
  //#endregion
}