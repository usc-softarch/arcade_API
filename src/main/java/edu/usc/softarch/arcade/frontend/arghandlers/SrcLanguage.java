package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.IOException;
import java.util.*; 
import java.util.stream.*; 

/**
 * Argument Handler for the source language of the project being analysed.
 * Currently supported languages are Java and C. Argument is case-insensitive.
 *
 * @author Marcelo Schmitt Laser
 */

public class SrcLanguage
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler srcLanguage;
  //#endregion

  //#region CONSTRUCTORS
  private SrcLanguage()
  {
    String name = "srcLanguage";
    String description = "Source Language";
    String instruction = "Source Language: This is the language of the source ";
    instruction += "code being analyzed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(srcLanguage == null) srcLanguage = new SrcLanguage();
    return srcLanguage;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
	  String[] supportedLanguages = {"C","c","Java","java"};//feel free to add more
	  boolean contains = Arrays.stream(supportedLanguages).anyMatch(value::equals);
	  if (!contains)
	       throw new IOException(value + "isn't supported");
	  
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
