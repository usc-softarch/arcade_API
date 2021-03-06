package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.File;

/**
 * Argument Handler for the path of a Interface Smell CSV file, typically of
 * the form *_interface_smells.csv.
 *
 * @author Khoi
 */
public class InterfaceSmellFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler interfaceSmellFile;
  //#endregion

  //#region CONSTRUCTORS
  private InterfaceSmellFile()
  {
    String name = "interfaceSmellFile";
    String description = "Interface Smell File";
    String instruction = "This is a file generated by an interface smell ";
    instruction += "detection technique, usually of the form ";
    instruction += "*_interface_smells.csv";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(interfaceSmellFile == null)
      interfaceSmellFile = new InterfaceSmellFile();
    return interfaceSmellFile;
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
    File interfaceSmellFileVar = new File(value);
    if(!interfaceSmellFileVar.getParentFile().exists()
      && !interfaceSmellFileVar.getParentFile().mkdirs())
        throw new Exception("Failed to create Interface Smell directory.");
    return true;
  }
  //#endregion
}
