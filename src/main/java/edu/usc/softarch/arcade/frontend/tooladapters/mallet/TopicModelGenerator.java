package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.Topicmodel;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletPath;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;

/**
 * Adapter for generating a topicmodel.data file through mallet.
 *
 * @author Marcelo Schmitt Laser
 */
public class TopicModelGenerator
  extends ToolAdapter
{
  //#region CONSTRUCTORS
  public TopicModelGenerator()
  {
    String id = "topicModelGenerator";
    String name = "Mallet: Topic Model Generator";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      Topicmodel.getInstance(),
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
    command.add("import-dir");
    command.add("--input");
    command.add(SourceDir.getInstance().getValue());
    command.add("--remove-stopwords");
    command.add("TRUE");
    command.add("--keep-sequence");
    command.add("TRUE");
    command.add("--output");
    command.add(Topicmodel.getInstance().getValue());
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
    boolean sourceDirValid = SourceDir.getInstance().validateAsInput();
    boolean topicmodelValid = Topicmodel.getInstance().validateAsOutput();
    boolean malletPathValid = MalletPath.getInstance().validateAsInput();
    boolean malletHomeValid = MalletHome.getInstance().validateAsInput();
    return (sourceDirValid && topicmodelValid
      && malletPathValid && malletHomeValid);
  }
  //#endregion
}
