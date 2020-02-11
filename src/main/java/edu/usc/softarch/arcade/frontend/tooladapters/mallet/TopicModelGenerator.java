package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.lang.reflect.InvocationTargetException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

/**
 * Adapter for generating a topicmodel.data file through mallet.
 *
 * @author Marcelo Schmitt Laser
 */
public class TopicModelGenerator
  extends ToolAdapter
{
  //#region CONSTRUCTOR
  /**
   * Base constructor. Initializes {@link #path} to empty String.
   *
   * @see edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter
   */
  public TopicModelGenerator() { }

  /**
   * Constructor with set path. If path does not exist, will throw exception
   * and terminate.
   *
   * @param path Path to the external tool's executable.
   * @throws FileNotFoundException If path does not exist. Does NOT check
   *                               whether path is an executable.
   * @see edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter
   */
  public TopicModelGenerator(String path)
    throws FileNotFoundException
  {
    super(path);
  }
  //#endregion

  //#region INTERFACE
  @Override
  public String getName()
  {
    return arcade.strings.components.topicModelGenerator.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.sourceDir.id,
      arcade.strings.args.outputDir.id
    };
  }

  @Override
  protected void exceptionHandling(Process p)
    throws InvocationTargetException
  {
    //TODO Check exit code
    //TODO If exit with error, initialize general purpose mallet exchandler
    //TODO general purpose mallet exchandler detects whether error is known
    // from looking at plain text error log
    //TODO if error is known, redirect to special purpose mallet exchandler
    // which builds and throws an InvocationTargetException
    //TODO create special purpose exchandler for blank space in path on
    // Windows error due to mallet being a piece of shit
    //TODO create special purpose exchandler for unexpected arguments due
    // to bad formatting e.g. spaces instead of separate arguments for TRUE
    // (maybe validate beforehand?)
  }

  @Override
  protected boolean validateArguments()
    throws IllegalArgumentException, IOException
  {
    // Check whether source directory exists
    File sourceDirectory
      = new File(getArguments().get(arcade.strings.args.sourceDir.id));
    if(!sourceDirectory.exists())
    {
      String errorMessage = "Source directory not found: ";
      errorMessage += getArguments().get(arcade.strings.args.sourceDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether output directory exists and, if not, create it
    String fs = File.separator;
    String outputDirPath = getArguments().get(arcade.strings.args.outputDir.id);
    outputDirPath += fs + "arc" + fs + "base";
    File outputDirectory = new File(outputDirPath);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    return true;
  }

  @Override
  protected List<String> buildCommandArguments()
  {
    List<String> command = new ArrayList<String>();
    String fs = File.separator;
    String sourceDir = getArguments().get(arcade.strings.args.sourceDir.id);
    String arcOutput = getArguments().get(arcade.strings.args.outputDir.id);
    arcOutput += fs + "arc" + fs + "base" + fs + "topicmodel.data";

    command.add("import-dir");
    command.add("--input");
    command.add(sourceDir);
    command.add("--remove-stopwords");
    command.add("TRUE");
    command.add("--keep-sequence");
    command.add("TRUE");
    command.add("--output");
    command.add(arcOutput);

    return command;
  }
  //#endregion
}
