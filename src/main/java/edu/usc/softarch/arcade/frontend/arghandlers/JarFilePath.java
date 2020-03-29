package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

/**
 * Argument Handler for the path of a Project.log file, typically of the form
 * *project.log.
 *
 * @author Khoi
 */
public class JarFilePath
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler jarFilePath;
  //#endregion

  //#region CONSTRUCTORS
  private JarFilePath()
  {
    String name = "jarFilePath";
    String description = "Jar File Path";
    String instruction = "Jar File Path: is the location of ";
    instruction += "executable jar file";
    instruction += "usually named: *.jar";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(jarFilePath == null) jarFilePath = new JarFilePath();
    return jarFilePath;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File jarFilePath = new File(value);
    if(!jarFilePath.exists())
      throw new FileNotFoundException(value + " not found.");
    else if ((value.length() < 4) 
			|| !(value.substring(value.length()-4).equals(".jar"))) 
			{		      
		      throw new IllegalArgumentException(value + "Invalid file type (must be .jar file)" );
		    }	   

    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {

    return true;
  }
 
  //#endregion
}