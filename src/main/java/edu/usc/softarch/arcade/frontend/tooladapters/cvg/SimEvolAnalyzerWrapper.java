package edu.usc.softarch.arcade.frontend.tooladapters.cvg;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

/**
 * Adapter for generating a pkg clustering files.
 *
 * @author Khoi
 */
public class SimEvolAnalyzerWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS	
  public SimEvolAnalyzerWrapper()
  {
    String id = "simevolanalyzer";
    String name = "cvg: Cluster Coverage";
    ArgHandler[] requiredArguments =
    {
    	WorkingDir.getInstance(),
    	PythonPackage.getInstance(),
    	ClusterDir.getInstance()    	
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region INTERNAL METHODS
  protected List<String> buildPrefix()
  {
    List<String> prefixes = new ArrayList<String>();
    prefixes.add("py");
    prefixes.add("-2");
    prefixes.add("-m");    
    return prefixes;
  }
  
  protected String buildWorkingDirectory()
  {
	String workingDir = new String();
    workingDir = (WorkingDir.getInstance().getValue());
    return workingDir;
  }  
  
  protected List<String> buildPythonPackage()
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
  
  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
	boolean workingDirValid = WorkingDir.getInstance().validateAsInput();
	boolean pythonPackageValid = PythonPackage.getInstance().validateAsInput();
    boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();        
    return (clusterDirValid && workingDirValid && pythonPackageValid);
  }
  //#endregion
}