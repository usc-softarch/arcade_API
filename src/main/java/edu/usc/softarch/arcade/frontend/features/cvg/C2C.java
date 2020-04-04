package edu.usc.softarch.arcade.frontend.features.cvg;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.tooladapters.cvg.SimEvolAnalyzerWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

/**
 * Workflow wrapper for CVG metrics.
 *
 * @author Marcelo Schmitt Laser
 */
public class C2C
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public C2C()
  {
    String id = "c2c";
    String name = "CVG: Full Workflow";
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

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    SimEvolAnalyzerWrapper seaw = new SimEvolAnalyzerWrapper();
    C2CAverageAnalyzeWrapper c2c = new C2CAverageAnalyzeWrapper();

    seaw.execute();
    c2c.execute();
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
