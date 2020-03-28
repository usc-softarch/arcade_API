package edu.usc.softarch.arcade.frontend.arghandlers;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Argument Handler for the file directory for processbuilder to redirect output to. This should always be
 * filled in automatically based on {@link }.
 *
 * @author Khoi
 */

public class WritingOutputDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler writingOutputDir;
  //#endregion

  //#region CONSTRUCTORS
  private WritingOutputDir()
  {
    String name = "writingOutputDir";
    String description = "Write Output Dir";
    String instruction = "Write Output Dir: This is the file dir to redirect processBuilder output to,";
    instruction += "preferably parent dir of python root package";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(writingOutputDir == null) writingOutputDir = new WritingOutputDir();
    return writingOutputDir;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  File WritingOutputDir = new File(value);
	    if(!WritingOutputDir.exists())
	      throw new IOException("WritingOutputDir path doesn't exist.");   
	    	    
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