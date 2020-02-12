package edu.usc.softarch.arcade.frontend.arghandlers;

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
  public boolean validate(String value)
    throws Exception
  {
    //TODO
    return true;
  }
  //#endregion
}
