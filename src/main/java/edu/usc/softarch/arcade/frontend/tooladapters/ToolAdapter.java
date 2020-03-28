package edu.usc.softarch.arcade.frontend.tooladapters;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

/**
 * Service interface for executing external tools.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ToolAdapter
  extends FeatureWrapper
{
  //#region INTERNAL METHODS
  /**
   * Builds the command for executing the tool.
   *
   * @return List of Strings that make up the command.
   */
  protected List<String> buildCommand()
  {
    List<String> command = new ArrayList<String>();
    command.addAll(buildPrefix());
    command.addAll(buildToolPath());
    command.addAll(buildArguments());
    return command;
  }

  /**
   * Constructs a String list with any necessary prefixes to execute the tool.
   * Must be overriden for non-executable tools (e.g. jar files).
   *
   * @return List of prefix commands (e.g. java -jar).
   * @see #buildCommand()
   */
  protected List<String> buildPrefix() { return new ArrayList<String>(); }

  /**
   * Constructs a String list with the appropriate path or name of the tool
   * being executed.
   *
   * @return List of tool path, name or other identifiers.
   * @see #buildCommand()
   */
  protected abstract List<String> buildToolPath();

  /**
   * Constructs a String list with the arguments for the execution command.
   *
   * @return List of Strings with arguments to be appended to base command.
   * @see #buildCommand()
   */
  protected abstract List<String> buildArguments();

  /**
   * Constructs a String list with the environment variables necessary for
   * executing the tool. Must be overwritten if temporary environment
   * variables are needed by the tool.
   *
   * @return Map of the environment variables to add.
   * @see #buildCommand()
   */
  protected Map<String,String> buildEnv()
    { return new HashMap<String,String>(); }

  /**
   * Executes any extra transformations to the ProcessBuilder object that
   * are required by a particular tool adapter. Default behavior is to do
   * nothing.
   *
   * @param pb The ProcessBuilder object responsible for executing the tool.
   */
  protected void executeAuxiliary(ProcessBuilder pb) { }
  //#endregion

  //#region INTERFACE
  @Override
  public void execute()
    throws Exception
  {
    List<String> command = buildCommand();

    try
    {
      ProcessBuilder pb = new ProcessBuilder(command);
      System.out.println(pb.command());
      pb.environment().putAll(buildEnv());
      pb.inheritIO();
      executeAuxiliary(pb);
      Process p = pb.start();
      p.waitFor();
    }
    catch(Exception e) { e.printStackTrace(); } //TODO
  }
  //#endregion
}
