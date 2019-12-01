package edu.usc.softarch.arcade.frontend.features.wrappers;

/**
 * The FeatureWrapper interface specifies the necessary operations for executing
 * an ARCADE feature. It streamlines the execution of a feature component.
 *
 * @author Marcelo Schmitt Laser
 */
public interface FeatureWrapper
{
  /**
   * Returns the feature component's name. The result may not match the class
   * or package name.
   *
   * @return Name of the feature component.
   */
  public String getName();

  /**
   * Executes the feature component's main functionality. If it contains more
   * than one primary functionality, it is advised that the component be
   * refactored.
   *
   * @param args All arguments necessary for execution of the feature.
   * @return The result of the execution, which must be recast for further use.
   * @throws Exception Any exception appropriate to the wrapped feature.
   */
  public Object[] execute(Object[] args)
    throws Exception;

  /**
   * Checks whether the arguments available are appropriate for usage with
   * this feature component.
   *
   * @param args Arguments to check.
   * @return False if arguments are invalid or insufficient.
   * @throws Exception Any exception appropriate to the wrapped feature.
   */
  public boolean checkArguments(String[] args)
    throws Exception;

  /**
   * Checks whether the arguments available are appropriate for usage with
   * this feature component.
   *
   * @param args Arguments to check.
   * @return False if arguments are invalid or insufficient.
   * @throws Exception Any exception appropriate to the wrapped feature.
   */
  public boolean checkArguments(Object[] args)
    throws Exception;
}
