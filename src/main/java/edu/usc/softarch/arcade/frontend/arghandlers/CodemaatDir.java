package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a directory Code-maat csv files.
 *  see {@link ClusterFile}.
 *  
 *  @author Khoi
 */

public class CodemaatDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler codemaatDir;
  //#endregion

  //#region CONSTRUCTORS
  private CodemaatDir()
  {
    String name = "codemaatDir";
    String description = "Code Maat Directory";
    String instruction = "Code Maat Directory: This is a directory ";
    instruction += "containing one or more code-maat .csv files.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(codemaatDir == null) codemaatDir = new CodemaatDir();
    return codemaatDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO	          
    File codemaatDirectory = new File(value);
    if(!codemaatDirectory.exists())
      throw new IOException("Code-Maat csv directory doesn't exist." + value);
    else
    {    	
    	File[] files = codemaatDirectory.listFiles();
        int dirLength = codemaatDirectory.list().length;
        boolean clusterFileContain = false;
        String filename;
    	for (int i =0; i<dirLength; i++)
    	{
    		filename = files[i].getName();
    		if ((filename.length() >= 4) && (filename.substring(filename.length()-4).equals(".csv")))
			{
				clusterFileContain = true;
				break;
			}				 
    	}
    	if(!clusterFileContain)
    	      throw new IOException("cluster directory doesn't have .csv file.");    		
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