package edu.usc.softarch.arcade.frontend.tooladapters.codemaat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectLogFile;
import edu.usc.softarch.arcade.frontend.arghandlers.JarFilePath;

/**
 * Adapter for generating an infer.mallet file through mallet.
 *
 * @author Marcelo Schmitt Laser
 */
public class CodemaatWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS
  public CodemaatWrapper()
  {
    String id = "Code-maat";
    String name = "Code-maat: Code-maat coupling format";
    ArgHandler[] requiredArguments =
    {     
      StdOutRedirect.getInstance(),
      JarFilePath.getInstance(),
      ProjectLogFile.getInstance() 
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
    toolPath.add(JarFilePath.getInstance().getValue());
    return toolPath;
  }    

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();
    command.add("-l");    
    command.add(ProjectLogFile.getInstance().getValue());
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
    boolean jarFilePathValid = JarFilePath.getInstance().validateAsOutput();
    boolean projectLogFileValid = ProjectLogFile.getInstance().validateAsInput();
    boolean stdOutRedirectValid = StdOutRedirect.getInstance().validateAsOutput();
    return (jarFilePathValid && projectLogFileValid && stdOutRedirectValid);
  }
  //#endregion
}
