package edu.usc.softarch.arcade.frontend.arghandlers;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

/**
 * Argument Handler for the path of a Smells SER file, typically of the form
 * *_smells.ser.
 *
 * @author Marcelo Schmitt Laser
 */
public class SmellsFile
  extends ArgHandler
{
  //#region ATTRIBUTES
  private static ArgHandler smellsFile;
  //#endregion

  //#region CONSTRUCTORS
  private SmellsFile()
  {
    String name = "smellsFile";
    String description = "Smells SER File";
    String instruction = "Smells SER File: This is a file generated by a smell";
    instruction += " detection technique, usually of the form *_smells.ser.";

    initialize(name, description, instruction);
  }

  public static ArgHandler getInstance()
  {
    if(smellsFile == null) smellsFile = new SmellsFile();
    return smellsFile;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean validateAsInput(String value)
    throws Exception
  {
    File smellsFile = new File(value);
    if(!smellsFile.exists())
      throw new FileNotFoundException(value + " not found.");

    return true;
  }

  @Override
  public boolean validateAsOutput(String value)
    throws Exception
  {
    File smellsFile = new File(value);
    if(!smellsFile.getParentFile().exists()
      && !smellsFile.getParentFile().mkdirs())
        throw new IOException("Failed to create output directory.");

    return true;
  }
  //#endregion
}
