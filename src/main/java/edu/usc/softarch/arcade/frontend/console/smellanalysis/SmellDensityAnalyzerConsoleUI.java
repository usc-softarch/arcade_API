package edu.usc.softarch.arcade.frontend.console.smellanalysis;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.smellanalysis.SmellDensityAnalyzerWrapper;

public class SmellDensityAnalyzerConsoleUI
  extends ConsoleUI
{
  FeatureWrapper smellAnalysis = new SmellDensityAnalyzerWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.smellAnalysis.density.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.smellAnalysis.density.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Smell Density Analyzer requires ";
    instructions += "the following arguments:" + cr;
    instructions += arcade.strings.args.smellsDir.instruction + cr;
    instructions += arcade.strings.args.clusterDir.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.smellsDir.id)
    		&& Console.arguments.containsKey(arcade.strings.args.clusterDir.id))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        return Console.arguments;
    }

    if(!useConfigArgument(arcade.strings.args.smellsDir.id))
      loadArgument(arcade.strings.args.smellsDir.id,
        arcade.strings.args.smellsDir.name);

    if(!useConfigArgument(arcade.strings.args.clusterDir.id))
        loadArgument(arcade.strings.args.clusterDir.id,
          arcade.strings.args.clusterDir.name);

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
	  smellAnalysis.checkArguments(args);
	  smellAnalysis.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    //TODO Add in requisites.
    return new String[]
      { };
  }
}
