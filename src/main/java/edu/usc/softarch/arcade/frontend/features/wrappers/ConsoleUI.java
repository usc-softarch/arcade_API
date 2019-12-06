package edu.usc.softarch.arcade.frontend.features.wrappers;

/**
 * The ConsoleUI interface specifies the necessary methods for use with the
 * {@link edu.usc.softarch.arcade.frontend.console} package.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ConsoleUI
{
  /**
   * Instance of the feature managed by this UI.
   */
  FeatureWrapper feature;

  /**
   * Returns a message to be displayed as a menu option by a console.
   *
   * @return Menu-option String.
   */
  public abstract String getMessage();

  /**
   * Returns a set of instructions for usage of this feature component.
   *
   * @return Instructions.
   */
  public abstract String getInstructions();

  /**
   * Full console interface for loading necessary arguments for execution of
   * the feature component.
   *
   * @return Array with necessary arguments loaded.
   * @see edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper#execute(Object[])
   */
  public abstract String[] loadArgumentsWizard();
}
