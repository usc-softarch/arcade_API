package edu.usc.softarch.arcade.frontend.arghandlers;

public class ProjectVersion
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler projectVersion;
  //#endregion

  //#region CONSTRUCTORS
  private ProjectVersion()
  {
    String name = "projectVersion";
    String description = "Project Version";
    String instruction = "This is the version of the project being analyzed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(projectVersion == null) projectVersion = new ProjectVersion();
    return projectVersion;
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
  //#endregion
}
