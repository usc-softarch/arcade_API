package edu.usc.softarch.arcade.frontend.arghandlers;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a Python working directory. This should always be
 * filled in automatically based on {@link }.
 *
 * @author Khoi
 */

public class WorkingDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler workingDir;
  //#endregion

  //#region CONSTRUCTORS
  private WorkingDir()
  {
    String name = "workingDir";
    String description = "Python Working Directory (by default src/main/resources)";
    String instruction = "Python Working Directory: This is the path to python module (-m) executable dir,";
    instruction += "preferably parent dir of python root package";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(workingDir == null) workingDir = new WorkingDir();
    return workingDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File WorkingDir = new File(value);
	    if(!WorkingDir.exists())
	      throw new IOException("Python file Path doesn't exist.");
	    else
	    { 
	    	File[] files = WorkingDir.listFiles();
	        int dirLength = WorkingDir.list().length;
	        boolean workingDirFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if (filename.equals("__init__.py")) 
	    		{
	    			workingDirFileContain = true;
					break;
				}				 
	    	}
	    	if(!workingDirFileContain)
	    	      throw new IOException("Python root package must have __init__.py file");    		
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
