package edu.usc.softarch.arcade.frontend.console;

import java.util.Map;

/**
 * Represents the front-end service provider of a component. This may be
 * either an Arcade feature or external tool.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ConsoleUI
{
  /**
   * Returns the adapted component's name. The result may not match the class
   * or package name. This name is used as an identifier within Arcade.
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
   * @return A map with all the necessary arguments.
   */
  public Map<String,String> loadArgumentsWizard();

  /**
   * Executes the adapted component's primary functionality.
   *
   * @param args A map with all the necessary arguments.
   * @throws Exception Any exception appropriate to the adapted component.
   */
  public void execute(Map<String,String> args)
    throws Exception;

  /**
   * Returns the names of all requisite components. These are those from which
   * the output serves as input to the selected one.
   *
   * @return Pre-requisite component names.
   */
  public String[] loadRequisites();
}
