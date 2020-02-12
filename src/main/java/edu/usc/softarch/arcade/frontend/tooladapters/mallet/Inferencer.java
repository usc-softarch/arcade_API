package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletPath;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;

/**
 * Adapter for generating an infer.mallet file through mallet.
 *
 * @author Marcelo Schmitt Laser
 */
public class Inferencer
  extends ToolAdapter
{
  //#region ATTRIBUTES
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler malletPath = MalletPath.getInstance();
  private static final ArgHandler malletHome = MalletHome.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return "inferencer";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      outputDir.getName(),
      malletPath.getName(),
      malletHome.getName()
    };
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildToolPath(Map<String,String> args)
  {
    List<String> toolPath = new ArrayList<String>();
    String malletPathVal = args.get(malletPath.getName());
    toolPath.add(malletPathVal);

    return toolPath;
  }

  @Override
  protected List<String> buildArguments(Map<String,String> args)
  {
    List<String> command = new ArrayList<String>();
    String fs = File.separator;
    String outputDirVal = args.get(outputDir.getName());
    outputDirVal += fs + "arc" + fs + "base" + fs;
    String topicModel = outputDirVal + "topicmodel.data";
    String arcOutput = outputDirVal + "infer.mallet";

    command.add("train-topics");
    command.add("--input");
    command.add(topicModel);
    command.add("--inferencer-filename");
    command.add(arcOutput);
    command.add("--num-top-words");
    command.add("50");
    command.add("--num-topics");
    command.add("100");
    command.add("--num-threads");
    command.add("3");
    command.add("--num-iterations");
    command.add("100");
    command.add("--doc-topics-threshold");
    command.add("0.1");

    return command;
  }

  @Override
  protected Map<String,String> buildEnv(Map<String,String> args)
  {
    Map<String,String> env = new HashMap<String,String>();
    env.put("MALLET_HOME", args.get(malletHome.getName()));
    return env;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws Exception
  {
    boolean outputDirValid = outputDir.validate(args);
    boolean malletPathValid = malletPath.validate(args);
    boolean malletHomeValid = malletHome.validate(args);

    return (outputDirValid && malletPathValid && malletHomeValid);
  }
  //#endregion
}
