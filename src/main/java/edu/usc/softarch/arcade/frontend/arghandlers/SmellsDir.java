package edu.usc.softarch.arcade.frontend.arghandlers;

import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

public class SmellsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler smellsDir;
  //#endregion

  //#region CONSTRUCTORS
  private SmellsDir()
  {
    String name = "smellsDir";
    String description = "Smells SER Directory";
    String instruction = "Smells SER Directory: This is a directory containing";
    instruction += " one or more files of the form *_smells.ser.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(smellsDir == null) smellsDir = new SmellsDir();
    return smellsDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File smellDirectory = new File(value);
	    if(!smellDirectory.exists() && !smellDirectory.mkdirs())
	      throw new IOException("Smells SER directory doesn't exist.");
	    else
	    {    	
	    	File[] files = smellDirectory.listFiles();
	        int dirLength = smellDirectory.list().length;
	        boolean smellFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if ((filename.length() >= 4) && (filename.substring(filename.length()-4).equals(".ser")))
				{
					smellFileContain = true;
					break;
				}				 
	    	}
	    	if(!smellFileContain)
	    	      throw new IOException("smells SER directory doesn't have .ser file.");    		
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
  
  @Override
  public boolean validate(String value)
    throws Exception
  {
    //TODO
	
    return true;
  }
  //#endregion
}
