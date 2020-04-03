package edu.usc.softarch.arcade.frontend.console;

import java.lang.String;
import java.lang.reflect.Method;
import java.lang.Class;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.Scanner;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

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
    loadConfiguration();
    ServiceLoader<FeatureWrapper> componentLoader
      = ServiceLoader.load(FeatureWrapper.class);
    Map<String, ConsoleUI> components = new HashMap<String, ConsoleUI>();

    for(FeatureWrapper comp : componentLoader)
    {
      components.put(comp.getId(), new ConsoleUI(comp));
    }

    // Hand over to Wizard
    if(args.length == 0)
      Wizard.execute(components);
    // -------------------
    else
    {
      try
      {
        // run(args, components);
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
  private static void run(Map<String, ConsoleUI> components)
    throws Exception
  {
    throw new UnsupportedOperationException();
    // ConsoleUI comp = components.get(args.get("feature"));
    // if(comp == null)
    // {
    //   IllegalArgumentException e = new IllegalArgumentException
    //     ("Component " + args.get("feature") + " not found.");
    //   throw e;
    // }
    //
    // comp.execute();
  }

  private static String installInstructions()
  {
    //TODO install instructions for new services
    throw new UnsupportedOperationException();
  }

  private static void loadConfiguration()
  {
    BufferedReader reader;
    try
    {
      reader = new BufferedReader(new FileReader("config.arcade"));
      String line = reader.readLine();
      while(line != null)
      {
        String[] lineArg = line.split("=");
        if(lineArg.length == 2)
        {
          lineArg[1] = lineArg[1].replace("\\", File.separator);
          Class argHandler = Class.forName(
            "edu.usc.softarch.arcade.frontend.arghandlers." + lineArg[0]);
          Method singletonCall = argHandler.getMethod("getInstance",null);
          ArgHandler toAssign = (ArgHandler)singletonCall.invoke(null,null);
          toAssign.setValue(lineArg[1]);
        }
        line = reader.readLine();
      }
      reader.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
