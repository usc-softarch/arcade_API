package edu.usc.softarch.arcade.frontend.features.wrappers;

/**
 * The ConsoleUI interface specifies the necessary methods for use with the
 * {@link edu.usc.softarch.arcade.frontend.console} package.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ConsoleUI
  extends FeatureWrapper
{
  /**
   * Returns a message to be displayed as a menu option by a console.
   *
   * @return Menu-option String.
   */
  public String getMessage();

  /**
   * Returns a set of instructions for usage of this feature component.
   *
   * @return Instructions.
   */
  public String getInstructions();

  /**
   * Full console interface for loading necessary arguments for execution of
   * the feature component.
   *
   * @return Array with necessary arguments loaded.
   * @see edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper#execute(Object[])
   */
  public String[] loadArgumentsWizard();
}
