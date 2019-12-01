package edu.usc.softarch.arcade.frontend.exttooladapters.util;

import java.io.FileNotFoundException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Service interface for executing external tools.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ExtToolAdapter
{
  //#region ATTRIBUTES
  /**
   * Path to the external tool's executable;
   */
  private String path = "";

  /**
   * Array of arguments for executing the tool.
   */
  private String[] arguments = { };

  /**
   * Map of environment variables.
   */
  private Map<String,String> environment = new HashMap<String,String>();
  //#endregion

  //#region CONSTRUCTOR
  /**
   * Base constructor. Initializes {@link #path} to empty String.
   */
  protected ExtToolAdapter() { }

  /**
   * Constructor with set path. If path does not exist, will throw exception
   * and terminate.
   *
   * @param path Path to the external tool's executable.
   * @throws FileNotFoundException If path does not exist. Does NOT check
   *                               whether path is an executable.
   */
  protected ExtToolAdapter(String path)
    throws FileNotFoundException
  {
    if(!setToolPath(path))
    {
      String error = "No executable found for " + getName() + " at " + path;
      throw new FileNotFoundException(error);
    }
  }
  //#endregion

  //#region ABSTRACT METHODS
  /**
   * Returns the name of the external tool. This name may include extra
   * information, such as its version.
   *
   * @return Name of the tool.
   */
  public abstract String getName();

  /**
   * Verifies whether the arguments are sufficient for execution and, if yes,
   * orders them appropriately.
   *
   * @param arguments List of arguments added to an instance of
   *                  {@code ExtToolAdapter}. For internal use only.
   * @return Validated arguments for execution of external tool.
   * @throws java.lang.IllegalArgumentException If an argument is incorrect
   *                                            or missing.
   * @see #execute()
   */
  protected abstract String[] validateArguments(String[] arguments)
    throws IllegalArgumentException;

  /**
   * Performs the tool-specific execution tasks.
   *
   * @param path Path to the external tool's executable. For internal use only.
   * @param arguments List of arguments added to an instance of
   *                  {@code ExtToolAdapter}. For internal use only.
   * @param environment Map of necessary environment variables. For internal
   *                    use only.
   * @return The result of the external tool's execution, if any.
   * @throws java.lang.reflect.InvocationTargetException If any errors occur
   *                                                     during the tool's
   *                                                     execution.
   * @see #execute()
   */
  protected abstract Object[] executeInternal(String path,
    String[] arguments, Map<String,String> environment)
    throws InvocationTargetException;
  //#endregion

  //#region INTERFACE
  /**
   * Sets path to the external tool's executable file. Does not check whether
   * the file referenced by the path is correct, only whether it exists.
   *
   * @param path Path to the external tool's executable.
   * @return True if file exists, false otherwise.
   */
  public boolean setToolPath(String path)
  {
    File testFile = new File(path);
    boolean exists;
    if(exists = testFile.exists())
      this.path = path;
    return exists;
  }

  /**
   * Gets path to the external tool's executable file. This is an auxiliary
   * accessor for peculiar situations. See
   * {@link edu.usc.softarch.arcade.frontend.exttooladapters.mallet.TopicModelGenerator#validateArguments(String[] arguments)}
   * for an example.
   *
   * @return Path to the external tool's executable.
   */
  protected String getToolPath()
  {
    return this.path;
  }

  /**
   * Adds one or more arguments for the execution of the external tool. Does
   * not perform any checks.
   *
   * @param args Arguments to be added. Order is irrelevant. Arguments should
   *             be formatted together with their flag, such as in
   *             {@code --Flag argument}.
   */
  public void addArguments(String[] args)
  {
    // For more information, see java.util.Arrays and java.util.stream.Stream.
    this.arguments =
      Stream.concat(Arrays.stream(arguments), Arrays.stream(args))
      .toArray(String[]::new);
  }

  /**
   * Adds one argument for the execution of the external tool. Does not perform
   * any checks.
   *
   * @param arg Argument to be added. Argument should be formatted together
   *            with its flag, such as in {@code --Flag argument}.
   */
  public void addArguments(String arg)
  {
    addArguments(new String[]{ arg });
  }

  /**
   * This method may be used to set an environment variable with which to
   * execute the external tool, when necessary. Only variables in addition to
   * the standard OS environment need to be included.
   *
   * @param env Map of environment variables of the form &lt;varName,value&gt;.
   */
  public void setEnvironment(Map<String,String> env)
  {
    this.environment = env;
  }

  /**
   * Executes the external tool. If an exception is encountered, it should be
   * handled by an appropriate
   * {@link edu.usc.softarch.arcade.frontend.features.wrappers.ExceptionHandler}.
   *
   * @return The result of the external tool's execution, if any.
   * @throws java.lang.reflect.InvocationTargetException If any errors occur
   *                                                     during the tool's
   *                                                     execution.
   * @throws java.lang.IllegalArgumentException If the provided arguments
   *                                            are invalid or insufficient.
   */
  public Object[] execute()
    throws InvocationTargetException, IllegalArgumentException
  {
    // This method serves to structure the execution process. The actual tasks
    // are implemented in the tool-specific methods.
    try { this.arguments = validateArguments(this.arguments); }
    catch(IllegalArgumentException e) { throw e; }

    try { return executeInternal(this.path, this.arguments, this.environment); }
    catch(InvocationTargetException e) { throw e; }
  }
  //#endregion
}
