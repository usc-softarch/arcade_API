package edu.usc.softarch.arcade.frontend.console.decaymetrics;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.decaymetrics.BatchDecayMetricsAnalyzerWrapper;

public class BatchDecayMetricsAnalyzerConsoleUI
  extends ConsoleUI
{
  FeatureWrapper decayMetrics = new BatchDecayMetricsAnalyzerWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.decayMetrics.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.decayMetrics.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Decay Metrics Analyzer requires ";
    instructions += "the following arguments:" + cr;
    instructions += arcade.strings.args.clusterDir.instruction + cr;
    instructions += arcade.strings.args.depsDir.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.clusterDir.id)
	      && Console.arguments.containsKey(arcade.strings.args.depsDir.id))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        return Console.arguments;
    }

    if(!useConfigArgument(arcade.strings.args.clusterDir.id))
      loadArgument(arcade.strings.args.clusterDir.id,
        arcade.strings.args.clusterDir.name);

    if(!useConfigArgument(arcade.strings.args.depsDir.id))
      loadArgument(arcade.strings.args.depsDir.id,
        arcade.strings.args.depsDir.name);

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    decayMetrics.checkArguments(args);
    decayMetrics.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    //TODO Fill in requisites
    return new String[]
      { };
  }
}
