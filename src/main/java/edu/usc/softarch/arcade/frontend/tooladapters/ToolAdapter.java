package edu.usc.softarch.arcade.frontend.tooladapters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

/**
 * Service interface for executing external tools.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ToolAdapter
  implements FeatureWrapper
{
  //#region INTERNAL METHODS
  /**
   * Builds the command for executing the tool.
   *
   * @return List of Strings that make up the command.
   */
  protected List<String> buildCommand(Map<String,String> args)
  {
    List<String> command = new ArrayList<String>();
    command.addAll(buildPrefix(args));
    command.addAll(buildToolPath(args));
    command.addAll(buildArguments(args));
    return command;
  }

  /**
   * Adds any necessary prefixes to execute the tool. Must be overriden for
   * non-executable tools (e.g. jar files).
   *
   * @return List of prefix commands (e.g. java -jar).
   * @see #buildCommand()
   */
  protected List<String> buildPrefix(Map<String,String> args)
  {
    return new ArrayList<String>();
  }

  protected abstract List<String> buildToolPath(Map<String,String> args);

  /**
   * Adds the arguments to the execution command.
   *
   * @return List of Strings with arguments to be appended to base command.
   * @see #buildCommand()
   */
  protected abstract List<String> buildArguments(Map<String,String> args);

  protected abstract Map<String,String> buildEnv(Map<String,String> args);
  //#endregion

  //#region INTERFACE
  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    List<String> command = buildCommand(args);

    try
    {
      ProcessBuilder pb = new ProcessBuilder(command);
      System.out.println(pb.command());
      pb.environment().putAll(buildEnv(args));
      pb.inheritIO();
      Process p = pb.start();
      p.waitFor();
    }
    catch(SecurityException e)
    {
      e.printStackTrace();
      //TODO throw exception from lack of permission
    }
    catch(IOException e)
    {
      e.printStackTrace();
      //TODO throw exception due to I/O error
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
      //TODO throw exception due to some crazy threading error
    }
    catch(NullPointerException | IllegalArgumentException e)
    {
      e.printStackTrace();
      //TODO throw exception due to herpaderp
    }
  }
  //#endregion
}
