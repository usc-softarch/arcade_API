package edu.usc.softarch.arcade.frontend.console;

/**
 * Driver for {@link edu.usc.softarch.arcade.frontend.console}.
 *
 * @author Marcelo Schmitt Laser
 */
public class Console
{
  public static void main(String[] args)
  {
    //TODO if !args.isEmpty(), try direct run
    //TODO else wizard()
  }

  /**
   * Bypasses console interface and tries to execute feature component directly.
   * If the requested service does not exist, displays instructions for
   * installing new services to {@code arcade_console}. If the arguments are
   * incorrect, displays an error message and instructions for usage of the
   * feature component. If the service exists and the arguments are correct,
   * initiates execution of the feature component.
   */
  private static void directRun()
  {
    //TODO try run feature with provided arguments
    //TODO if service doesn't exist, display install instructions
    /*TODO if !service.checkArguments(),
        service.wrongArgumentsMessage() + getInstructions()*/
    //TODO else hand over to execution pipeline
  }

  /**
   * Runs a console-based wizard for choosing and executing an ARCADE service.
   */
  private static void wizard()
  {
    //TODO load services
    /*TODO for each service, System.out.println(option# + service.getName() +
        service.getMessage())*/
    //TODO get user input
    //TODO if input valid, service.getInstructions()
    //TODO service.loadArgumentsWizard()
    //TODO service.execute()
  }
}
