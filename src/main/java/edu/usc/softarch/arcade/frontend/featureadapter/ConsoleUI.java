package edu.usc.softarch.arcade.frontend.featureadapter;

/**
 * The ConsoleUI interface specifies the necessary methods for use with the
 * {@link edu.usc.softarch.arcade.frontend.console} package.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ConsoleUI
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
   * @return An Object array with the necessary arguments loaded.
   * @see edu.usc.softarch.arcade.frontend.featureadapter.FeatureAdapter#execute(Object[])
   */
  public Object[] loadArgumentsWizard();
}
