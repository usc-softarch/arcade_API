package edu.usc.softarch.arcade.frontend.features.arc;

import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.console.Console;

public class ArcConsoleUI
  implements ConsoleUI
{
  FeatureWrapper arc = new ArcWrapper();

  @Override
  public String getName()
  {
    return "arc";
  }

  @Override
  public String getMessage()
  {
    return "ARC: Architectural Recovery based on Concerns";
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "ARC requires the following arguments:" + cr;
    instructions += "Input Directory: This is a directory containing one or ";
    instructions += "more subdirectories with different version of the ";
    instructions += "subject system." + cr;
    instructions += "Output Directory: This is the directory where all output ";
    instructions += "files go. It must contain a subdirectory called base ";
    instructions += "that contains the files infer.mallet and output.pipe. ";
    instructions += "If running ARC from the console wizard, these will be ";
    instructions += "generated and organized for you." + cr;
    instructions += "Classes Directory: This is the directory in each ";
    instructions += "subject system that contains the compiled classes of ";
    instructions += "that system version." + cr;

    return instructions;
  }

  @Override
  public String[] loadArgumentsWizard()
  {
    System.out.println("Please enter input directory: ");
    String inputDir = Console.in.nextLine();

    System.out.println("Please enter output directory: ");
    String outputDir = Console.in.nextLine();

    System.out.println("Please enter classes directory: ");
    String classesDir = Console.in.nextLine();

    return new String[] { inputDir, outputDir, classesDir };
  }

  @Override
  public Object[] execute(String[] args)
    throws Exception
  {
    arc.checkArguments(args);
    return arc.execute(args);
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]
      { "pipeextractor", "topicmodelgenerator", "inferencer" };
  }
}
