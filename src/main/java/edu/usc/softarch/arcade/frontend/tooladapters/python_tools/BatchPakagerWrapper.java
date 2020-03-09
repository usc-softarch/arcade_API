package edu.usc.softarch.arcade.frontend.tooladapters.python_tools;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.usc.softarch.arcade.frontend.tooladapters.ToolAdapter;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.PkgPrefixes;

/**
 * Adapter for generating a pkg clustering files.
 *
 * @author Marcelo Schmitt Laser
 */
public class BatchPakagerWrapper
  extends ToolAdapter
{
  //#region CONSTRUCTORS
  public BatchPakagerWrapper()
  {
    String id = "batchpackager";
    String name = "pkg: Clustering deps.rsf files";
    ArgHandler[] requiredArguments =
    {
    	DepsDir.getInstance(),
    	PkgPrefixes.getInstance()
    };

    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region INTERNAL METHODS
  @Override
  protected List<String> buildToolPath()
  {
    return ;
  }

  @Override
  protected List<String> buildArguments()
  {
    List<String> command = new ArrayList<String>();
    command.add("py");
    command.add("-2");
    command.add("batchpackager.py");
    command.add("--startdir");
    command.add(DepsDir.getInstance().getValue());
    command.add("--pkgprefixes");    
    command.add(PkgPrefixes.getInstance().getValue());
    return command;
  }

  @Override
  protected Map<String,String> buildEnv()
  {    
    return ;
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean depsDirValid = DepsDir.getInstance().validateAsInput();
    boolean pkgPrefixesValid = PkgPrefixes.getInstance().validateAsOutput();    
    return (depsDirValid && pkgPrefixesValid);
  }
  //#endregion
}
