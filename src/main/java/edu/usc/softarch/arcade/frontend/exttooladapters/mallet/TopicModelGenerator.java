package edu.usc.softarch.arcade.frontend.exttooladapters.mallet;

import java.lang.reflect.InvocationTargetException;
import java.io.FileNotFoundException;
import java.io.File;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.DefaultETAdapter;

//TODO document this class properly
public class TopicModelGenerator
  extends DefaultETAdapter
{
  //#region CONSTRUCTOR
  /**
   * Base constructor. Initializes {@link #path} to empty String.
   *
   * @see edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter
   */
  public TopicModelGenerator()
  {
    super
    (
      "import-dir|--remove-stopwords TRUE|--keep-sequence TRUE",
      "--input|--output"
    );
  }

  /**
   * Constructor with set path. If path does not exist, will throw exception
   * and terminate.
   *
   * @param path Path to the external tool's executable.
   * @throws FileNotFoundException If path does not exist. Does NOT check
   *                               whether path is an executable.
   * @see edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter
   */
  public TopicModelGenerator(String path)
    throws FileNotFoundException
  {
    super
    (
      path,
      "import-dir|--remove-stopwords TRUE|--keep-sequence TRUE",
      "--input|--output"
    );
  }
  //#endregion

  //#region INTERFACE
  @Override
  public String getName() { return "topicmodelgenerator"; }

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
  protected boolean createOutputDir(String[] args)
  {
    for(String s : args)
    {
      String[] tokens = s.split(" ");
      if(tokens[0].equals("--output"))
      {
        File outputPath = new File(tokens[1]);
        return outputPath.getParentFile().mkdirs();
      }
    }

    return false;
  }
  //#endregion
}