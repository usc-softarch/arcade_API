package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
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
  //#region ATTRIBUTES
  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler malletPath = MalletPath.getInstance();
  private static final ArgHandler malletHome = MalletHome.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getName()
  {
    return "topicModelGenerator";
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      sourceDir.getName(),
      outputDir.getName(),
      malletPath.getName(),
      malletHome.getName()
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
    String fs = File.separator;
    String sourceDirVal = sourceDir.getValue();
    String outputDirVal = outputDir.getValue();
    outputDirVal += fs + "arc" + fs + "base" + fs + "topicmodel.data";

    command.add("import-dir");
    command.add("--input");
    command.add(sourceDirVal);
    command.add("--remove-stopwords");
    command.add("TRUE");
    command.add("--keep-sequence");
    command.add("TRUE");
    command.add("--output");
    command.add(outputDirVal);

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
    boolean sourceDirValid = sourceDir.validate();
    boolean outputDirValid = outputDir.validate();
    boolean malletPathValid = malletPath.validate();
    boolean malletHomeValid = malletHome.validate();

    return (sourceDirValid && outputDirValid
      && malletPathValid && malletHomeValid);
  }
  //#endregion
}
