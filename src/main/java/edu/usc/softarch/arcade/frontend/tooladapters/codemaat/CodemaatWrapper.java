package edu.usc.softarch.arcade.frontend.tooladapters.codemaat;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.GitProjectLog;
import edu.usc.softarch.arcade.frontend.arghandlers.CodeMaatPath;

public class CodemaatWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS
  public CodemaatWrapper()
  {
    String id = "CodeMaat";
    String name = "Code Maat";
    ArgHandler[] requiredArguments =
    {
      StdOutRedirect.getInstance(),
      CodeMaatPath.getInstance(),
      GitProjectLog.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildPrefix()
  {
    List<String> prefixes = new ArrayList<String>();
    prefixes.add("java");
    prefixes.add("-jar");
    return prefixes;
  }

  @Override
  protected List<String> buildToolPath()
  {
    List<String> toolPath = new ArrayList<String>();
    toolPath.add(CodeMaatPath.getInstance().getValue());
    return toolPath;
  }

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();
    command.add("-l");
    command.add(GitProjectLog.getInstance().getValue());
    command.add("-c");
    command.add("git2");
    command.add("-a");
    command.add("coupling");
    return command;
  }

  @Override
  protected void executeAuxiliary(ProcessBuilder pb)
  {
    pb.redirectOutput(new File(StdOutRedirect.getInstance().getValue()));
  }

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean codeMaatPathValid = CodeMaatPath.getInstance().validateAsOutput();
    boolean projectLogFileValid = GitProjectLog.getInstance().validateAsInput();
    boolean stdOutValid = StdOutRedirect.getInstance().validateAsOutput();
    return (codeMaatPathValid && projectLogFileValid && stdOutValid);
  }
  //#endregion
}
