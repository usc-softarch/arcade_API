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
    String sourceDirVal = args.get(sourceDir.getName());
    String outputDirVal = args.get(outputDir.getName());
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
    boolean sourceDirValid = sourceDir.validate(args);
    boolean outputDirValid = outputDir.validate(args);
    boolean malletPathValid = malletPath.validate(args);
    boolean malletHomeValid = malletHome.validate(args);

    return (sourceDirValid && outputDirValid
      && malletPathValid && malletHomeValid);
  }
  //#endregion
}
