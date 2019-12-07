package edu.usc.softarch.arcade.frontend.exttooladapters.mallet;

import org.apache.commons.lang3.SystemUtils;
import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.console.ConsoleUI;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter;
import edu.usc.softarch.arcade.frontend.console.Console;

public class InferencerConsoleUI
  implements ConsoleUI
{
  ExtToolAdapter inferencer = new Inferencer();

  @Override
  public String getName()
  {
    return "inferencer";
  }

  @Override
  public String getMessage()
  {
    return "Mallet: Inferencer";
  }

  @Override
  public String getInstructions()
  {
    String cr = System.lineSeparator();
    String instructions = "Mallet's inference requires the following arguments:"
      + cr + "Input: This is the path to the topicmodel.data file generated ";
    instructions += "by Mallet's topic model generator. " + cr;
    instructions += "Output: This is the path of the output ";
    instructions += "file. It must be called infer.mallet, and must be in "
      + "a subdirectory called base ";
    instructions += "inside the main output directory. ";
    instructions += "If running as a requisite of ARC from the console wizard" +
      ", the base subdirectory will be ";
    instructions += "generated and organized for you." + cr;
    instructions += "Tool path: path to mallet's executable (mallet for Linux "
      + "or mallet.bat for Windows)" + cr;

    return instructions;
  }

  @Override
  public String[] loadArgumentsWizard()
  {
    System.out.println("Please enter path to topicmodel.data file: ");
    String input = "--input " + Console.in.nextLine();

    System.out.println("Please enter path to output file: ");
    String output = "--inferencer-filename " + Console.in.nextLine();

    System.out.println("Please enter path to mallet's executable: ");
    String malletPath = Console.in.nextLine();

    inferencer.setToolPath(malletPath);

    if(SystemUtils.IS_OS_WINDOWS)
    {
      System.out.print("The system has detected that you are executing from");
      System.out.print(" a Windows OS machine. Please enter the path to ");
      System.out.print("Mallet's home directory in order to set a temporary ");
      System.out.println("environment variable.");

      String malletEnv = Console.in.nextLine();
      Map<String,String> environment = new HashMap<String,String>();
      environment.put("MALLET_HOME", malletEnv);
      inferencer.setEnvironment(environment);
    }

    return new String[] { input, output };
  }

  @Override
  public Object[] execute(String[] args)
    throws Exception
  {
    inferencer.addArguments(args);
    return inferencer.execute();
  }

  @Override
  public String[] loadRequisites()
  {
    return new String[]{ "pipeextractor", "topicmodelgenerator" };
  }
}
