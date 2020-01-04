package edu.usc.softarch.arcade.frontend.console.acdc;

import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.console.Console;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.acdc.AcdcWrapper;

public class AcdcConsoleUI
  implements ConsoleUI
{
  FeatureWrapper acdc = new AcdcWrapper();

  @Override
  public String getName()
  {
    return "acdc";
  }

  @Override
  public String getMessage()
  {
    return "ACDC: Algorithm for Comprehension-Driven Clustering";
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "ACDC requires the following arguments:" + cr;
    instructions += "Source Directory: This is a directory containing one or ";
    instructions += "more subdirectories with different versions of the ";
    instructions += "subject system." + cr;
    instructions += "Output Directory: This is the directory where all output ";
    instructions += "files go. It must contain a subdirectory called base ";
    instructions += "that contains the files infer.mallet and output.pipe. ";
    instructions += cr;
    instructions += "Classes Directory: This is the directory in each ";
    instructions += "subject system that contains the compiled classes of ";
    instructions += "that system version." + cr;

    return instructions;
  }

  @Override
  public Map<String,String> loadArgumentsWizard()
  {
    // Use existing config
    if(Console.arguments.containsKey("sourceDir")
      && Console.arguments.containsKey("acdcOutput")
      && Console.arguments.containsKey("binDir"))
    {
      System.out.print("All arguments found in configuration. ");
      System.out.println("Use existing arguments? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        return Console.arguments;
    }

    Map<String,String> result = new HashMap<String,String>();

    if(Console.arguments.containsKey("sourceDir"))
    {
      System.out.print("Argument sourceDir found in configuration. ");
      System.out.println("Use existing configuration? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        result.put("sourceDir", Console.arguments.get("sourceDir"));
    }

    if(!result.containsKey("sourceDir"))
    {
      System.out.println("Please enter source directory: ");
      String sourceDir = Console.in.nextLine();
      result.put("sourceDir", sourceDir);
      Console.arguments.put("sourceDir", sourceDir);
    }

    if(Console.arguments.containsKey("acdcOutput"))
    {
      System.out.print("Argument acdcOutput found in configuration. ");
      System.out.println("Use existing configuration? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        result.put("acdcOutput", Console.arguments.get("acdcOutput"));
    }

    if(!result.containsKey("acdcOutput"))
    {
      System.out.println("Please enter output directory: ");
      String acdcOutput = Console.in.nextLine();
      result.put("acdcOutput", acdcOutput);
      Console.arguments.put("acdcOutput", acdcOutput);
    }

    if(Console.arguments.containsKey("binDir"))
    {
      System.out.print("Argument binDir found in configuration. ");
      System.out.println("Use existing configuration? (y/n)");
      String choice = Console.in.nextLine();
      if(choice.equals("y"))
        result.put("binDir", Console.arguments.get("binDir"));
    }

    if(!result.containsKey("binDir"))
    {
      System.out.println("Please enter classes directory: ");
      String binDir = Console.in.nextLine();
      result.put("binDir", binDir);
      Console.arguments.put("binDir", binDir);
    }

    return result;
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception
  {
    acdc.checkArguments(args);
    acdc.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]
      { };
  }
}
