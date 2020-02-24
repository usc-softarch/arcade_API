package edu.usc.softarch.arcade.frontend.arghandlers;

import java.lang.String;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

public class ProjectName
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler projectName;
  //#endregion

  //#region CONSTRUCTORS
  private ProjectName()
  {
    String name = "projectName";
    String description = "Project Name";
    String instruction = "This is the name of the project being analyzed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(projectName == null) projectName = new ProjectName();
    return projectName;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    //TODO
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
