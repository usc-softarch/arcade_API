package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path to mallet's installation directory. Not to be
 * confused with the path to mallet's executable, {@link MalletPath}.
 *
 * @author Marcelo Schmitt Laser
 */

public class MalletHome
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler malletHome;
  //#endregion

  //#region CONSTRUCTORS
  private MalletHome()
  {
    String name = "malletHome";
    String description = "Mallet Home Directory";
    String instruction = "This is the path to the directory where your local ";
    instruction += "mallet installation is.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(malletHome == null) malletHome = new MalletHome();
    return malletHome;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
	  File MalletHome = new File(value);
    if(!MalletHome.exists() && !MalletHome.mkdirs())
    {
      String errorMessage = "Mallet Home directory not found at path: ";
      errorMessage += value;
      throw new Exception(errorMessage);
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
