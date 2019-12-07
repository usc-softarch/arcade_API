package edu.usc.softarch.arcade.frontend.exttooladapters.util;

import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Map;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter;

/**
 * Default implementation of the majority of ExtToolAdapter. May be used to
 * minimize the amount of code necessary to create an adaptor for a new
 * external tool. Includes facilities for executing .jar files and for
 * creating necessary directory structures.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class DefaultETAdapter
  extends ExtToolAdapter
{
  //#region ATTRIBUTES
  /**
   * Regular expression pattern of arguments that may be ignored by validation.
   * May be used to ignore validation of static arguments, that is, arguments
   * that may not be set by the user.
   * <p>
   * Examples may be found in the constructors of
   * {@link edu.usc.softarch.arcade.frontend.exttooladapters.mallet.Inferencer}
   * and
   * {@link edu.usc.softarch.arcade.frontend.exttooladapters.mallet.TopicModelGenerator}.
   */
  private String ignore;

  /**
   * Regular expression pattern of arguments that must be provided by the user.
   * <p>
   * Examples may be found in the constructors of
   * {@link edu.usc.softarch.arcade.frontend.exttooladapters.mallet.Inferencer}
   * and
   * {@link edu.usc.softarch.arcade.frontend.exttooladapters.mallet.TopicModelGenerator}.
   */
  private String require;

  /**
   * Boolean attribute representing whether or not the identifier of arguments
   * should be included as an argument for the external tool. For example, if
   * argument "--num 5" is provided and prepend is true, arguments "--num" and
   * "5" will be included; if prepend is false, "--num" will be ignored and
   * "5" will be included.
   */
  private boolean prepend;

  /**
   * Boolean attribute representing whether or not the external tool is a .jar
   * file. If true, the commands "java -jar" will be prepended to the execution
   * command automatically.
   */
  private boolean isJar;
  //#endregion

  //#region CONSTRUCTOR
  /**
   * Short constructor. Sets {@link #prepend} to true and {@link #isJar} to
   * false.
   *
   * @param ignore {@see #ignore}
   * @param require {@see #require}
   */
  public DefaultETAdapter(String ignore, String require)
  {
    initialize(ignore, require, true, false);
  }

  /**
   * Short constructor with path. Sets {@link #prepend} to true and
   * {@link #isJar} to false. Verifies whether {@code path} is a valid file.
   *
   * @param path Path to the external tool's executable.
   * @param ignore {@see #ignore}
   * @param require {@see #require}
   */
  public DefaultETAdapter(String path, String ignore, String require)
    throws FileNotFoundException
  {
    super(path);
    initialize(ignore, require, true, false);
  }

  /**
   * Complete constructor.
   *
   * @param ignore {@see #ignore}
   * @param require {@see #require}
   * @param prepend {@see #prepend}
   * @param isJar {@see #isJar}
   */
  public DefaultETAdapter(
    String ignore, String require, boolean prepend, boolean isJar)
  {
    super();
    initialize(ignore, require, prepend, isJar);
  }

  /**
   * Complete constructor. Verifies whether {@code path} is a valid file.
   *
   * @param ignore {@see #ignore}
   * @param require {@see #require}
   * @param prepend {@see #prepend}
   * @param isJar {@see #isJar}
   */
  public DefaultETAdapter(
    String path, String ignore, String require, boolean prepend, boolean isJar)
    throws FileNotFoundException
  {
    super(path);
    initialize(ignore, require, prepend, isJar);
  }

  // Support method for constructors.
  private void initialize(
    String ignore, String require, boolean prepend, boolean isJar)
  {
    this.ignore = ignore;
    this.require = require;
    this.prepend = prepend;
    this.isJar = isJar;
  }
  //#endregion

  //#region ABSTRACT_METHODS
  /**
   * Handles exceptions thrown by the external tool's process.
   *
   * @param p External tool's process.
   */
  protected abstract void exceptionHandling(Process p)
    throws InvocationTargetException;

  protected abstract boolean createOutputDir(String[] args);
  //#endregion

  //#region INTERFACE
  //TODO add order validation
  @Override
  protected String[] validateArguments(String[] arguments)
    throws IllegalArgumentException
  {
    // Result list
    List<String> newArguments = new ArrayList<String>();
    // Support variable to count provided arguments
    int numFlags = require.split(Pattern.quote("|")).length;
    // Support list to verify whether an argument is provided more than once
    List<String> flags = new ArrayList<String>();

    // Adds static arguments to result
    if(!this.ignore.isEmpty())
    {
      String[] constantArguments = ignore.split(Pattern.quote("|") + "| ");
      for(String s : constantArguments) { newArguments.add(s); }
    }

    createOutputDir(arguments);

    for(String s : arguments)
    {
      if(!s.matches(ignore))
      {
        String[] tokens = s.split(" ");
        if(!tokens[0].matches(require))
          throw new IllegalArgumentException("Unknown argument " + tokens[0]);
        if(flags.contains(tokens[0]))
          throw new IllegalArgumentException("Repeat argument " + tokens[0]);

        flags.add(tokens[0]);
        if(prepend) newArguments.add(tokens[0]);
        newArguments.add(tokens[1]);
      }
    }

    if(flags.size() != numFlags);
      //TODO Throw exception due to missing arguments

    return newArguments.toArray(new String[0]);
  }

  @Override
  protected Object[] executeInternal(String path,
    String[] arguments, Map<String,String> environment)
    throws InvocationTargetException
  {
    List<String> command = new ArrayList<String>();
    //TODO comment this
    if(this.isJar)
    {
      command.add("java");
      command.add("-jar");
    }
    command.add(path);
    for(String s : arguments)
      command.add(s);

    try
    {
      ProcessBuilder pb = new ProcessBuilder(command);
      System.out.println(pb.command());
      pb.environment().putAll(environment);
      pb.inheritIO();
      Process p = pb.start();
      p.waitFor();
      exceptionHandling(p);
    }
    catch(SecurityException e)
    {
      e.printStackTrace();
      //TODO throw exception from lack of permission
    }
    catch(IOException e)
    {
      e.printStackTrace();
      //TODO throw exception due to I/O error
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
      //TODO throw exception due to some crazy threading error
    }
    catch(NullPointerException | IllegalArgumentException e)
    {
      e.printStackTrace();
      //TODO throw exception due to herpaderp
    }

    return null;
  }
  //#endregion
}
