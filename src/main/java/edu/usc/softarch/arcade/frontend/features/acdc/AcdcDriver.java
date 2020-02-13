package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.acdc.AcdcWrapper;
import edu.usc.softarch.arcade.frontend.features.smelldetection.ArchSmellDetectorWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.JavaSourceToDepsBuilderWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;

public class AcdcDriver
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private FeatureWrapper acdc = new AcdcWrapper();
  private FeatureWrapper archsmelldetector = new ArchSmellDetectorWrapper();
  private FeatureWrapper depsbuilder = new JavaSourceToDepsBuilderWrapper();

  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler binDir = BinDir.getInstance();
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  private static final ArgHandler clusterFile = ClusterFile.getInstance();
  private static final ArgHandler smellsFile = SmellsFile.getInstance();
  //#endregion

  //#region CONFIGURATION
  @Override
  public String getId() { return "acdcDriver"; }

  @Override
  public String getName() { return "ACDC Workflow Driver"; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      sourceDir.getName(),
      outputDir.getName(),
      binDir.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
      outputDir,
      binDir
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    // Setting up Variables
    String fs = File.separator;
    // TODO FIX THIS LATER (check ArchSmeelDetetor.java in Arcade to see
    // TODO how Duc Le retrieve version name)
    String projectName = "Struts2";

    // Dependency Builder
    depsbuilder.checkArguments();
    depsbuilder.execute();

    // Architectural Recovery
    String outputDirVal = outputDir.getValue();
    String depsRsfFileVal =
      outputDirVal + fs + "commonRes" + fs + projectName + "_deps.rsf";
    depsRsfFile.setValue(depsRsfFileVal);
    String clusterFileVal =
      outputDirVal + fs + "commonRes" + fs + projectName + "_cluster.rsf";
    clusterFile.setValue(clusterFileVal);

    acdc.checkArguments();
    acdc.execute();

    // Smell Detection
    String smellsFileVal =
      outputDir + fs + "commonRes" + fs + projectName + "_smells.ser";
    smellsFile.setValue(smellsFileVal);

    archsmelldetector.checkArguments();
    archsmelldetector.execute();
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments()
    throws Exception
  {
    boolean sourceDirValid = sourceDir.validate();
    boolean outputDirValid = outputDir.validate();
    boolean binDirValid = binDir.validate();

    return (sourceDirValid && outputDirValid && binDirValid);
  }
  //#endregion
}
