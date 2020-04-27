package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the package prefixes of the subject system to be
 * analyzed.
 *
 * @author Khoi
 * @see edu.usc.softarch.arcade.frontend.tooladapters.pkg.batchPackagerWrapper
 */
public class PkgPrefixes
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler pkgPrefixes;
  //#endregion

  //#region CONSTRUCTORS
  private PkgPrefixes()
  {
    String name = "pkgPrefixes";
    String description = "Package Prefixes";
    String instruction = "This is the set of package prefixes for the ";
    instruction += "subject system being analyzed.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(pkgPrefixes == null) pkgPrefixes = new PkgPrefixes();
    return pkgPrefixes;
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
