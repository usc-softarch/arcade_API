package edu.usc.softarch.arcade.frontend.arghandlers;

import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

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
    //TODO
	  File MalletHome = new File(value);
	    if(!MalletHome.exists() && !MalletHome.mkdirs())
	      throw new IOException("Mallet Home directory doesn't exist."); 
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
