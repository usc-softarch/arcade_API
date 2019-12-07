package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.console.Console;

public class PipeExtractorConsoleUI
  implements ConsoleUI
{
  FeatureWrapper pipeextractor = new PipeExtractorWrapper();

  @Override
  public String getName()
  {
    return "pipeextractor";
  }

  @Override
  public String getMessage()
  {
    return "PipeExtractor";
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "PipeExtractor requires the following arguments:"
      + cr + "Source Directory: This is a directory containing one or ";
    instructions += "more subdirectories with different version of the ";
    instructions += "subject system." + cr;
    instructions += "Output Directory: This is the directory where the output ";
    instructions += "file goes. It must be a subdirectory called base ";
    instructions += "inside the main output directory. ";
    instructions += "If running as a requisite of ARC from the console wizard" +
      ", the base subdirectory will be ";
    instructions += "generated and organized for you." + cr;

    return instructions;
  }

  @Override
  public String[] loadArgumentsWizard()
  {
    System.out.println("Please enter source directory: ");
    String sourceDir = Console.in.nextLine();

    System.out.println("Please enter output directory: ");
    String outputDir = Console.in.nextLine();

    return new String[] { sourceDir, outputDir };
  }

  @Override
  public Object[] execute(String[] args)
    throws Exception
  {
    pipeextractor.checkArguments(args);
    return pipeextractor.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]{ };
  }
}
