package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * Argument Handler for the path to a directory containing _deps.rsf files.
 * For the path to a specific _deps.rsf file, see {@link DepsRsfFile}.
 */
public class DepsDir
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler depsDir;
  //#endregion

  //#region CONSTRUCTORS
  private DepsDir()
  {
    String name = "depsDir";
    String description = "Dependencies RSF Directory";
    String instruction = "Dependencies RSF Directory: This is a directory ";
    instruction += "containing one or more files of the form *_deps.rsf.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(depsDir == null) depsDir = new DepsDir();
    return depsDir;
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
