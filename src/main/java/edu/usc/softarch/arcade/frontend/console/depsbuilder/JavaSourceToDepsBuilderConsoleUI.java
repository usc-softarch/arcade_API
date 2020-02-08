package edu.usc.softarch.arcade.frontend.console.depsbuilder;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.JavaSourceToDepsBuilderWrapper;

public class JavaSourceToDepsBuilderConsoleUI
  extends ConsoleUI
{
  FeatureWrapper depsBuilder = new JavaSourceToDepsBuilderWrapper();

  @Override
  public String getName()
  {
    return arcade.strings.components.depsBuilder.java.id;
  }

  @Override
  public String getMessage()
  {
    return arcade.strings.components.depsBuilder.java.message;
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Java Fact Extractor requires the following arguments:" + cr;
    instructions += arcade.strings.args.sourceDir.instruction + cr;
    instructions += arcade.strings.args.depsRsfFile.instruction + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey(arcade.strings.args.sourceDir.id)
      && Console.arguments.containsKey(arcade.strings.args.depsRsfFile.id))
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

    if(!useConfigArgument(arcade.strings.args.depsRsfFile.id))
      loadArgument(arcade.strings.args.depsRsfFile.id,
        arcade.strings.args.depsRsfFile.name);

    return argumentBuilder;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    depsBuilder.checkArguments(args);
    depsBuilder.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    //TODO fill in requisites
    return new String[]
      { };
  }
}
