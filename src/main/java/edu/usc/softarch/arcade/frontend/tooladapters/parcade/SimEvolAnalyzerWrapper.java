package edu.usc.softarch.arcade.frontend.tooladapters.parcade;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.CVGResultsFile;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonWorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

/**
 * Adapter for the Python ARCADE tool for pairwise computation of the CVG
 * architectural metric.
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
    	PythonWorkingDir.getInstance(),
    	CVGResultsFile.getInstance(),
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

  @Override
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
    pb.directory(new File(PythonWorkingDir.getInstance().getValue()));
    pb.redirectOutput(new File(CVGResultsFile.getInstance().getValue()));
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean workingDirValid = PythonWorkingDir.getInstance().validateAsInput();
    boolean pythonPackageValid = PythonPackage.getInstance().validateAsInput();
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
    boolean cvgResultsFileValid =
      CVGResultsFile.getInstance().validateAsOutput();
    return (clusterDirValid && workingDirValid
     && pythonPackageValid && cvgResultsFileValid);
  }
  //#endregion
}
