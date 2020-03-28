package edu.usc.softarch.arcade.frontend.tooladapters.cvg;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

/**
 * Adapter for generating a pkg clustering file.
 *
 * @author Khoi
 */
public class SimEvolAnalyzerWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS
  public SimEvolAnalyzerWrapper()
  {
    String id = "simEvolAnalyzer";
    String name = "CVG: Cluster Coverage";
    ArgHandler[] requiredArguments =
    {
    	WorkingDir.getInstance(),
    	StdOutRedirect.getInstance(),
    	PythonPackage.getInstance(),
    	ClusterDir.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildPrefix()
  {
    List<String> prefixes = new ArrayList<String>();
    prefixes.add("py");
    prefixes.add("-2");
    prefixes.add("-m");
    return prefixes;
  }

  protected List<String> buildToolPath()
  {
    List<String> pythonPackage = new ArrayList<String>();
    pythonPackage.add(PythonPackage.getInstance().getValue());
    return pythonPackage;
  }

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();
    command.add("--inputdir");
    command.add(ClusterDir.getInstance().getValue());
    return command;
  }

  @Override
  protected void executeAuxiliary(ProcessBuilder pb)
  {
    pb.directory(new File(WorkingDir.getInstance().getValue()));
    pb.redirectOutput(new File(StdOutRedirect.getInstance().getValue()));
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean workingDirValid = WorkingDir.getInstance().validateAsInput();
    boolean pythonPackageValid = PythonPackage.getInstance().validateAsInput();
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    boolean stdOutRedirectValid =
      StdOutRedirect.getInstance().validateAsOutput();
    return (clusterDirValid && workingDirValid
      && pythonPackageValid && stdOutRedirectValid);
  }
  //#endregion
}
