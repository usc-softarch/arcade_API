package edu.usc.softarch.arcade.frontend.featureadapter;

/**
 * The FeatureAdapter interface specifies the necessary operations for executing
 * an ARCADE feature. It streamlines the execution of a feature component.
 *
 * @author Marcelo Schmitt Laser
 */
public interface FeatureAdapter
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
   * @return The result of the execution, which must be recast for further use.
   */
  public Object[] execute(Object[] args);

  /**
   * Checks whether the arguments available are appropriate for usage with
   * this feature component.
   */
  public boolean checkArguments(String[] args);

  /**
   * Checks whether the arguments available are appropriate for usage with
   * this feature component.
   */
  public boolean checkArguments(Object[] args);
}
