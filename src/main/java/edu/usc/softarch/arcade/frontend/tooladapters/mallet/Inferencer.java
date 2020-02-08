package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

/**
 * Adapter for generating an infer.mallet file through mallet.
 *
 * @author Marcelo Schmitt Laser
 */
public class Inferencer
  extends ToolAdapter
{
  //#region CONSTRUCTOR
  /**
   * Base constructor. Initializes {@link #path} to empty String.
   *
   * @param isJar {@link #isJar}
   * @see edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter
   */
  public Inferencer(boolean isJar)
  {
    super(isJar);
  }

  /**
   * Constructor with set path. If path does not exist, will throw exception
   * and terminate.
   *
   * @param path Path to the external tool's executable.
   * @param isJar {@link #isJar}
   * @throws FileNotFoundException If path does not exist. Does NOT check
   *                               whether path is an executable.
   * @see edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter
   */
  public Inferencer(String path, boolean isJar)
    throws FileNotFoundException
  {
    super(path, isJar);
  }
  //#endregion

  //#region INTERFACE
  @Override
  public String getName()
  {
    return arcade.strings.components.inferencer.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[] { arcade.strings.args.outputDir.id };
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
    String fs = File.separator;

    // Check whether topicmodel.data exists
    String topicModelPath =
      getArguments().get(arcade.strings.args.outputDir.id);
    topicModelPath += fs + "arc" + fs + "base" + fs + "topicmodel.data";
    File topicModel = new File(topicModelPath);
    if(!topicModel.exists())
      throw new IllegalArgumentException("topicmodel.data file not found.");

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
    String topicModel = getArguments().get(arcade.strings.args.outputDir.id);
    topicModel += fs + "arc" + fs + "base" + fs + "topicmodel.data";
    String arcOutput = getArguments().get(arcade.strings.args.outputDir.id);
    arcOutput += fs + "arc" + fs + "base" + fs + "infer.mallet";

    command.add("train-topics");
    command.add("--input");
    command.add(topicModel);
    command.add("--inferencer-filename");
    command.add(arcOutput);
    command.add("--num-top-words");
    command.add("50");
    command.add("--num-topics");
    command.add("100");
    command.add("--num-threads");
    command.add("3");
    command.add("--num-iterations");
    command.add("100");
    command.add("--doc-topics-threshold");
    command.add("0.1");

    return command;
  }
  //#endregion
}
