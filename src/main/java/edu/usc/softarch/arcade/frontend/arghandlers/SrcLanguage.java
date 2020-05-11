package edu.usc.softarch.arcade.frontend.arghandlers;

import java.util.Arrays;
import java.util.List;

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
    String instruction = "This is the language of the source code being ";
    instruction += "analyzed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(srcLanguage == null) srcLanguage = new SrcLanguage();
    return srcLanguage;
  }
  //#endregion

  //#region ACCESSORS
  @Override
  public String setValue(String value)
  {
    return super.setValue(value.toLowerCase());
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    List<String> supportedLanguages = Arrays.asList("c","java");
	  if(!supportedLanguages.contains(value))
      throw new Exception(value + " isn't supported.");
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
