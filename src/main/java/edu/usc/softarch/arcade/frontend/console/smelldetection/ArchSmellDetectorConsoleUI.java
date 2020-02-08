package edu.usc.softarch.arcade.frontend.console.smelldetection;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.smelldetection.ArchSmellDetectorWrapper;

public class ArchSmellDetectorConsoleUI
  extends ConsoleUI
{
  FeatureWrapper smellDetector = new ArchSmellDetectorWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.archSmellDetector.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.archSmellDetector.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Architectural Smell Detector requires ";
    instructions += "the following arguments:" + cr;
    instructions += arcade.strings.args.depsRsfFile.instruction + cr;
    instructions += arcade.strings.args.clusterFile.instruction + cr;
    instructions += arcade.strings.args.smellsFile.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.depsRsfFile.id)
  		  && Console.arguments.containsKey(arcade.strings.args.clusterFile.id)
  	      && Console.arguments.containsKey(arcade.strings.args.smellsFile.id))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        return Console.arguments;
    }

    if(!useConfigArgument(arcade.strings.args.depsRsfFile.id))
      loadArgument(arcade.strings.args.depsRsfFile.id,
        arcade.strings.args.depsRsfFile.name);

    if(!useConfigArgument(arcade.strings.args.clusterFile.id))
        loadArgument(arcade.strings.args.clusterFile.id,
          arcade.strings.args.clusterFile.name);

    if(!useConfigArgument(arcade.strings.args.smellsFile.id))
      loadArgument(arcade.strings.args.smellsFile.id,
        arcade.strings.args.smellsFile.name);

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
	  smellDetector.checkArguments(args);
	  smellDetector.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    //TODO Write in the requisites
    return new String[]
      { };
  }
}
