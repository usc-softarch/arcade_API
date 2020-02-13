package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.Topicmodel;
import edu.usc.softarch.arcade.frontend.arghandlers.InferenceFile;
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
  private static final ArgHandler topicmodel = Topicmodel.getInstance();
  private static final ArgHandler inferenceFile = InferenceFile.getInstance();
  private static final ArgHandler malletPath = MalletPath.getInstance();
  private static final ArgHandler malletHome = MalletHome.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId()
  {
    return "inferencer";
  }

  @Override
  public String getName()
  {
    return "Mallet: Inferencer";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      topicmodel.getName(),
      inferenceFile.getName(),
      malletPath.getName(),
      malletHome.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      topicmodel,
      inferenceFile,
      malletPath,
      malletHome
    };
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildToolPath()
  {
    List<String> toolPath = new ArrayList<String>();
    String malletPathVal = malletPath.getValue();
    toolPath.add(malletPathVal);

    return toolPath;
  }

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();

    command.add("train-topics");
    command.add("--input");
    command.add(topicmodel.getValue());
    command.add("--inferencer-filename");
    command.add(inferenceFile.getValue());
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
  protected Map<String,String> buildEnv()
  {
    Map<String,String> env = new HashMap<String,String>();
    env.put("MALLET_HOME", malletHome.getValue());
    return env;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean topicmodelValid = topicmodel.validate();
    boolean inferenceFileValid = inferenceFile.validate();
    boolean malletPathValid = malletPath.validate();
    boolean malletHomeValid = malletHome.validate();

    return (topicmodelValid && inferenceFileValid
      && malletPathValid && malletHomeValid);
  }
  //#endregion
}
