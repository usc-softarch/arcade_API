package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the path to a directory containing _cluster.rsf files.
 * For the path to a specific _cluster.rsf file, see {@link ClusterFile}.
 */

public class ClusterDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler clusterDir;
  //#endregion

  //#region CONSTRUCTORS
  private ClusterDir()
  {
    String name = "clusterDir";
    String description = "Cluster RSF Directory";
    String instruction = "Cluster RSF Directory: This is a directory ";
    instruction += "containing one or more files of the form *_cluster.rsf.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(clusterDir == null) clusterDir = new ClusterDir();
    return clusterDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO	          
    File clusterDirectory = new File(value);
    if(!clusterDirectory.exists() && !clusterDirectory.mkdirs())
      throw new IOException("cluster directory doesn't exist.");
    else
    {    	
    	File[] files = clusterDirectory.listFiles();
        int dirLength = clusterDirectory.list().length;
        boolean clusterFileContain = false;
        String filename;
    	for (int i =0; i<dirLength; i++)
    	{
    		filename = files[i].getName();
    		if ((filename.length() >= 4) && (filename.substring(filename.length()-4).equals(".rsf")))
			{
				clusterFileContain = true;
				break;
			}				 
    	}
    	if(!clusterFileContain)
    	      throw new IOException("cluster directory doesn't have .rsf file.");    		
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
