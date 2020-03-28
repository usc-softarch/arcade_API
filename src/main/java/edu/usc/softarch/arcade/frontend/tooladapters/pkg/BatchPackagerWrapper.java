package edu.usc.softarch.arcade.frontend.tooladapters.pkg;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.PkgPrefixes;

/**
 * Adapter for generating a pkg clustering files.
 *
 * @author Khoi
 */
public class BatchPackagerWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS	
  public BatchPackagerWrapper()
  {
    String id = "batchpackager";
    String name = "pkg: Batch Packager Reference";
    ArgHandler[] requiredArguments =
    {
    	WorkingDir.getInstance(),
    	PythonPackage.getInstance(),
    	DepsDir.getInstance(),
    	PkgPrefixes.getInstance()
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
    command.add("--startdir");
    command.add(DepsDir.getInstance().getValue());
    command.add("--pkgprefixes");    
    command.add(PkgPrefixes.getInstance().getValue());
    return command;
  }  
  
  @Override
  protected void executeAuxiliary(ProcessBuilder pb)
  {
    pb.directory(new File(WorkingDir.getInstance().getValue()));
  }
  //#endregion
  
  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
	boolean workingDirValid = WorkingDir.getInstance().validateAsInput();
	boolean pythonPackageValid = PythonPackage.getInstance().validateAsInput();
    boolean depsDirValid = DepsDir.getInstance().validateAsInput();
    boolean pkgPrefixesValid = PkgPrefixes.getInstance().validateAsOutput();    
    return (depsDirValid && pkgPrefixesValid 
    		&& workingDirValid && pythonPackageValid);
  }
  //#endregion
}