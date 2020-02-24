package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.lang.String;
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
  //#region CONSTRUCTORS
  public Inferencer()
  {
    String id = "inferencer";
    String name = "Mallet: Inferencer";
    ArgHandler[] requiredArguments =
    {
      Topicmodel.getInstance(),
      InferenceFile.getInstance(),
      MalletPath.getInstance(),
      MalletHome.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildToolPath()
  {
    List<String> toolPath = new ArrayList<String>();
    toolPath.add(MalletPath.getInstance().getValue());
    return toolPath;
  }

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();
    command.add("train-topics");
    command.add("--input");
    command.add(Topicmodel.getInstance().getValue());
    command.add("--inferencer-filename");
    command.add(InferenceFile.getInstance().getValue());
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
    env.put("MALLET_HOME", MalletHome.getInstance().getValue());
    return env;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean topicmodelValid = Topicmodel.getInstance().validateAsInput(Topicmodel.getInstance().getValue());
    boolean inferenceFileValid = InferenceFile.getInstance().validateAsOutput(InferenceFile.getInstance().getValue());
    boolean malletPathValid = MalletPath.getInstance().validateAsInput(MalletPath.getInstance().getValue());
    boolean malletHomeValid = MalletHome.getInstance().validateAsInput(MalletHome.getInstance().getValue());
    return (topicmodelValid && inferenceFileValid
      && malletPathValid && malletHomeValid);
  }
  //#endregion
}
