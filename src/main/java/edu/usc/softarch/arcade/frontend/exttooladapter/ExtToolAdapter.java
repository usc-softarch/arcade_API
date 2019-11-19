package edu.usc.softarch.arcade.frontend.exttooladapter;

import java.lang.reflect.InvocationTargetException;

/**
 * Service interface for executing external tools.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ExtToolAdapter
{
  /**
   * Returns the name of the external tool. This name may include extra
   * information, such as its version.
   *
   * @return Name of the tool.
   */
  public String getName();

  /**
   * Sets path to the external tool's executable file. Does not check whether
   * the file referenced by the path is correct, only whether it exists.
   *
   * @return True if file exists, false otherwise.
   */
  public boolean setToolPath(String path);

  /**
   * Sets the necessary arguments for the execution of the external tool.
   */
  public void setArguments(String[] args);

  /**
   * Executes the external tool. If an exception is encountered, it should be
   * handled by an appropriate
   * {@link edu.usc.softarch.arcade.frontend.featurewrapper.ExceptionHandler}.
   *
   * @return The result of the external tool's execution, if any.
   * @throws java.lang.reflect.InvocationTargetException
   */
  public Object[] execute()
    throws InvocationTargetException;
}
