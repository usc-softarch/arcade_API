package edu.usc.softarch.arcade.frontend.console;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

/**
 * Represents the front-end service provider of a component. This may be
 * either an Arcade feature or external tool.
 *
 * @author Marcelo Schmitt Laser
 */
public class ConsoleUI
{
  private FeatureWrapper wrappedFeature;

  public ConsoleUI(FeatureWrapper wrappedFeature)
  {
    this.wrappedFeature = wrappedFeature;
  }

  /**
   * Returns the adapted component's name. The result may not match the class
   * or package name. This name is used as an identifier within Arcade.
   *
   * @return Name of the feature component.
   */
  public String getId() { return wrappedFeature.getId(); }

  /**
   * Returns a message to be displayed as a menu option.
   *
   * @return Menu option message.
   */
  public String getName() { return wrappedFeature.getName(); }

  /**
   * Returns usage instructions for the adapted functional component.
   *
   * @return Use instructions.
   */
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = getName() + " requires ";
    instructions += "the following arguments:" + cr;
    for(ArgHandler argHandler : wrappedFeature.getArgumentHandlers())
      instructions += argHandler.getInstruction() + cr;

    return instructions;
  }

  /**
   * Initiates a wizard for loading the arguments required by the adapted
   * functional component.
   *
   * @return A map with all the necessary arguments.
   */
  public void loadArgumentsWizard()
  {
    for(ArgHandler argHandler : wrappedFeature.getArgumentHandlers())
      loadArgument(argHandler);
  }

  /**
   * Executes the adapted component's primary functionality.
   *
   * @param args A map with all the necessary arguments.
   * @throws Exception Any exception appropriate to the adapted component.
   */
  public void execute()
    throws Exception
  {
    wrappedFeature.checkArguments();
	  wrappedFeature.execute();
  }

  private boolean useConfigArgument(ArgHandler argHandler)
  {
    try
    {
      argHandler.validate();
      String toPrint = "Argument " + argHandler.getName() + " found in ";
      toPrint += "configuration. Use existing configuration? (y/n)";
      System.out.println(toPrint);
      String choice = Console.in.nextLine();
      return choice.equals("y");
    }
    catch(Exception e)
    {
      e.printStackTrace();
      //TODO treatment
    }
    return false;
  }

  protected void loadArgument(ArgHandler argHandler)
  {
    if(!useConfigArgument(argHandler))
    {
      System.out.println("Please enter " + argHandler.getDescription() + ": ");
      String newArg = Console.in.nextLine();
      argHandler.setValue(newArg);
    }
  }
}
