package edu.usc.softarch.arcade.frontend.console.arc;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.arc.ArcWrapper;

public class ArcConsoleUI
  extends ConsoleUI
{
  FeatureWrapper arc = new ArcWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.arc.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.arc.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "ARC requires the following arguments:" + cr;
    instructions += arcade.strings.args.sourceDir.instruction + cr;
    instructions += arcade.strings.args.outputDir.instruction + cr;
    instructions += arcade.strings.args.binDir.instruction + cr;
    instructions += arcade.strings.args.language.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.sourceDir.id)
      && Console.arguments.containsKey(arcade.strings.args.outputDir.id)
      && Console.arguments.containsKey(arcade.strings.args.binDir.id)
      && Console.arguments.containsKey(arcade.strings.args.language.id))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        return Console.arguments;
    }

    if(!useConfigArgument(arcade.strings.args.sourceDir.id))
      loadArgument(arcade.strings.args.sourceDir.id,
        arcade.strings.args.sourceDir.name);

    if(!useConfigArgument(arcade.strings.args.outputDir.id))
      loadArgument(arcade.strings.args.outputDir.id,
        arcade.strings.args.outputDir.name);

    if(!useConfigArgument(arcade.strings.args.binDir.id))
      loadArgument(arcade.strings.args.binDir.id,
        arcade.strings.args.binDir.name);

    if(!useConfigArgument(arcade.strings.args.language.id))
      loadArgument(arcade.strings.args.language.id,
        arcade.strings.args.language.name);

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    arc.checkArguments(args);
    arc.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]
      {
        arcade.strings.components.pipeExtractor.id,
        arcade.strings.components.topicModelGenerator.id,
        arcade.strings.components.inferencer.id
      };
  }
}
