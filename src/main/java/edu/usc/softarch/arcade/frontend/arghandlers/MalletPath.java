package edu.usc.softarch.arcade.frontend.arghandlers;

import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

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
	    if(!MalletPath.exists() && !MalletPath.mkdirs())
	      throw new IOException("Mallet Path directory doesn't exist.");
	    else
	    {    	
	    	File[] files = MalletPath.listFiles();
	        int dirLength = MalletPath.list().length;
	        boolean malletFileContain = false;
	        String filename;
	    	for (int i =0; i<dirLength; i++)
	    	{
	    		filename = files[i].getName();
	    		if (filename.equals("mallet"))
				{
					malletFileContain = true;
					break;
				}				 
	    	}
	    	if(!malletFileContain)
	    	      throw new IOException("Mallet Path directory missing file mallet.");    		
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
