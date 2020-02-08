package edu.usc.softarch.arcade.frontend.console.mallet;

import org.apache.commons.lang3.SystemUtils;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;
import edu.usc.softarch.arcade.frontend.tooladapters.mallet.TopicModelGenerator;

public class TopicModelGeneratorConsoleUI
  extends ConsoleUI
{
  ToolAdapter tpm = new TopicModelGenerator(false);

  @Override
  public String getName()
  {
    return arcade.strings.components.topicModelGenerator.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.topicModelGenerator.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Mallet's topic model generation requires the ";
    instructions += "following arguments:" + cr;
    instructions += arcade.strings.args.sourceDir.instruction + cr;
    instructions += arcade.strings.args.outputDir.instruction + cr;
    instructions += arcade.strings.args.malletPath.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    if(SystemUtils.IS_OS_WINDOWS) { setEnvironment(); }

    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.sourceDir.id)
      && Console.arguments.containsKey(arcade.strings.args.outputDir.id)
      && Console.arguments.containsKey(arcade.strings.args.malletPath.id))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
      {
        tpm.setToolPath(
          Console.arguments.get(arcade.strings.args.malletPath.id));
        return Console.arguments;
      }
    }

    if(!useConfigArgument(arcade.strings.args.sourceDir.id))
      loadArgument(arcade.strings.args.sourceDir.id,
        arcade.strings.args.sourceDir.name);

    if(!useConfigArgument(arcade.strings.args.outputDir.id))
      loadArgument(arcade.strings.args.outputDir.id,
        arcade.strings.args.outputDir.name);

    boolean pathSet = false;

    if(Console.arguments.containsKey(arcade.strings.args.malletPath.id))
    {
      System.out.print("Argument malletPath found in configuration. ");
      System.out.println("Use existing configuration? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
      {
        tpm.setToolPath(
          Console.arguments.get(arcade.strings.args.malletPath.id));
        pathSet = true;
      }
    }

    if(!pathSet)
    {
      System.out.println("Please enter path to mallet's executable: ");
      String malletPath = Console.in.nextLine();
      tpm.setToolPath(Console.arguments.get(arcade.strings.args.malletPath.id));
      Console.arguments.put(arcade.strings.args.malletPath.id, malletPath);
    }

    return argumentBuilder;
  }

  private void setEnvironment()
  {
    System.out.print("Arcade has detected that you are executing from a ");
    System.out.println("Windows OS machine.");
    Map<String,String> environment = new HashMap<String,String>();

    if(Console.arguments.containsKey("malletHome"))
    {
      System.out.print("Argument malletHome found in configuration. ");
      System.out.println("Use existing configuration? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        environment.put("MALLET_HOME", Console.arguments.get("malletHome"));
    }

    if(!environment.containsKey("MALLET_HOME"))
    {
      System.out.print("Please enter the path to Mallet's home directory in");
      System.out.println("order to set a temporary environment variable:");
      String malletHome = Console.in.nextLine();
      environment.put("MALLET_HOME", malletHome);
      Console.arguments.put("malletHome", malletHome);
    }

    tpm.setEnvironment(environment);
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    tpm.addArguments(args);
    tpm.execute();
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]{ arcade.strings.components.pipeExtractor.id };
  }
}
