package edu.usc.softarch.arcade.frontend.console;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.Scanner;

/**
 * Driver for {@link edu.usc.softarch.arcade.frontend.console}.
 *
 * @author Marcelo Schmitt Laser
 */
public class Console
{
  public static Scanner in = new Scanner(System.in);

  public static void main(String[] args)
  {
    ServiceLoader<ConsoleUI> componentLoader = ServiceLoader.load(ConsoleUI.class);
    Map<String, ConsoleUI> components = new HashMap<String, ConsoleUI>();

    for(ConsoleUI comp : componentLoader)
    {
      components.put(comp.getName(), comp);
    }

    // Hand over to Wizard
    if(args.length == 0)
      Wizard.execute(components);
    // -------------------
    else
    {
      try
      {
        run(args, components);
      }
      catch(Exception e)
      {
        System.out.println(e.getMessage() + System.lineSeparator());

        if(e.getClass().equals(IllegalArgumentException.class))
        {
          StackTraceElement thrower = e.getStackTrace()[0];
          if(e.getCause() != null)
            thrower = e.getCause().getStackTrace()[0];
          String throwerName = thrower.getClassName() + "#"
            + thrower.getMethodName();

          if(throwerName.equals("edu.usc.softarch.arcade.frontend.console#run"))
          {
            System.out.println(installInstructions());
          }
        }
      }
    }
  }

  /**
   * Tries to execute feature component directly.
   * <p>
   * If the requested service does not exist, displays instructions for
   * installing new services to {@code arcade_console}. If the arguments are
   * incorrect, displays an error message and instructions for usage of the
   * feature component. If the service exists and the arguments are correct,
   * initiates execution of the feature component.
   */
  private static void run(String[] args, Map<String, ConsoleUI> components)
    throws Exception
  {
    ConsoleUI comp = components.get(args[0]);
    if(comp == null)
    {
      IllegalArgumentException e = new IllegalArgumentException
        ("Component " + args[0] + " not found.");
      throw e;
    }

    comp.execute(Arrays.copyOfRange(args, 1, args.length));
  }

  private static String installInstructions()
  {
    //TODO install instructions for new services
    throw new UnsupportedOperationException();
  }
}
