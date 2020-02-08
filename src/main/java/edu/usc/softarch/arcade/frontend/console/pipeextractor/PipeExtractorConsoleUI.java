package edu.usc.softarch.arcade.frontend.console.pipeextractor;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.pipeextractor.PipeExtractorWrapper;

public class PipeExtractorConsoleUI
  extends ConsoleUI
{
  FeatureWrapper pipeextractor = new PipeExtractorWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.pipeExtractor.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.pipeExtractor.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Pipe Extractor requires the following arguments:";
    instructions += arcade.strings.args.sourceDir.instruction + cr;
    instructions += arcade.strings.args.outputDir.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.sourceDir.id)
      && Console.arguments.containsKey(arcade.strings.args.outputDir.id))
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

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    pipeextractor.checkArguments(args);
    pipeextractor.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]{ };
  }
}
