package edu.usc.softarch.arcade.frontend.exttooladapters.mallet;

import java.lang.reflect.InvocationTargetException;
import java.io.FileNotFoundException;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.DefaultETAdapter;

//TODO document this class properly
public class Inferencer
  extends DefaultETAdapter
{
  //#region CONSTRUCTOR
  /**
   * Base constructor. Initializes {@link #path} to empty String.
   *
   * @see edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter
   */
  public Inferencer()
  {
    super
    (
      "train-topics|--num-top-words 50|--num-topics 100|--num-threads 3|--num-iterations 100|--doc-topics-threshold 0.1",
      "--input|--inferencer-filename"
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
  public Inferencer(String path)
    throws FileNotFoundException
  {
    super
    (
      path,
      "train-topics|--num-top-words 50|--num-topics 100|--num-threads 3|--num-iterations 100|--doc-topics-threshold 0.1",
      "--input|--inferencer-filename"
    );
  }
  //#endregion

  //#region INTERFACE
  @Override
  public String getName() { return "Inferencer"; }

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
  //#endregion
}
