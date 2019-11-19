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
    //TODO if args.isEmpty(), args = Wizard.execute()
    //TODO run(args)
  }

  /**
   * Tries to execute feature component directly.
   * If the requested service does not exist, displays instructions for
   * installing new services to {@code arcade_console}. If the arguments are
   * incorrect, displays an error message and instructions for usage of the
   * feature component. If the service exists and the arguments are correct,
   * initiates execution of the feature component.
   */
  private static void run(String[] args)
  {
    //TODO try run feature with provided arguments
    //TODO if service doesn't exist, display install instructions
    /*TODO if !service.checkArguments(),
        service.wrongArgumentsMessage() + getInstructions()*/
    //TODO else hand over to execution pipeline
  }
}
