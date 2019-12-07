package edu.usc.softarch.arcade.frontend.console;

/**
 * Represents the Console UI element of a functional component. This may be
 * either an Arcade feature or external tool.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ConsoleUI
{
  /**
   * Returns the adapted component's name. The result may not match the class
   * or package name.
   *
   * @return Name of the feature component.
   */
  public String getName();

  /**
   * Returns a message to be displayed as a menu option.
   *
   * @return Menu option message.
   */
  public String getMessage();

  /**
   * Returns usage instructions for the adapted functional component.
   *
   * @return Use instructions.
   */
  public String getInstructions();

  /**
   * Initiates a wizard for loading the arguments required by the adapted
   * functional component.
   *
   * @return An array with all the necessary arguments.
   */
  public String[] loadArgumentsWizard();

  /**
   * Executes the adapted functional component's primary functionality.
   *
   * @return The result of execution, if any.
   */
  public Object[] execute(String[] args)
    throws Exception;

  /**
   * Returns the names of all requisite components. These are those from which
   * the output serves as input to the selected one.
   *
   * @return Pre-requisite component names.
   */
  public String[] loadRequisites();
}
