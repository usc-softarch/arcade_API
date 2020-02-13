package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.acdc.AcdcWrapper;
import edu.usc.softarch.arcade.frontend.features.smelldetection.ArchSmellDetectorWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.JavaSourceToDepsBuilderWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.CSourceToDepsBuilderWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;

public class AcdcDriver
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private FeatureWrapper acdc = new AcdcWrapper();
  private FeatureWrapper archsmelldetector = new ArchSmellDetectorWrapper();
  private FeatureWrapper depsbuilder;

  private static final ArgHandler sourceDir = SourceDir.getInstance();
  private static final ArgHandler outputDir = OutputDir.getInstance();
  private static final ArgHandler binDir = BinDir.getInstance();
  private static final ArgHandler depsRsfFile = DepsRsfFile.getInstance();
  private static final ArgHandler clusterFile = ClusterFile.getInstance();
  private static final ArgHandler smellsFile = SmellsFile.getInstance();
  private static final ArgHandler srcLanguage = SrcLanguage.getInstance();
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
      binDir.getName(),
      srcLanguage.getName()
    };
  }

  @Override
  public ArgHandler[] getArgumentHandlers()
  {
    return new ArgHandler[]
    {
      sourceDir,
      outputDir,
      binDir,
      srcLanguage
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception, IOException, IllegalArgumentException
  {
    // Setting up Arguments
    String fs = File.separator;

    // TODO FIX THIS LATER (check ArchSmeelDetetor.java in Arcade to see
    // TODO how Duc Le retrieve version name)
    String projectName = "Struts2";

    String depsRsfFileVal = outputDir.getValue() + fs + "commonRes" + fs
      + projectName + "_deps.rsf";
    depsRsfFile.setValue(depsRsfFileVal);
    String clusterFileVal = outputDir.getValue() + fs + "acdc" + fs
      + projectName + "_cluster.rsf";
    clusterFile.setValue(clusterFileVal);
    String smellsFileVal = outputDir.getValue() + fs + "acdc" + fs
      + projectName + "_smells.ser";
    smellsFile.setValue(smellsFileVal);

    // Dependency Builder
    if(srcLanguage.getValue().toLowerCase().equals("java"))
      depsbuilder = new JavaSourceToDepsBuilderWrapper();
    if(srcLanguage.getValue().toLowerCase().equals("c"))
      depsbuilder = new CSourceToDepsBuilderWrapper();
    depsbuilder.checkArguments();
    depsbuilder.execute();

    // Architectural Recovery
    acdc.checkArguments();
    acdc.execute();

    // Smell Detection
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
    boolean srcLanguageValid = srcLanguage.validate();

    return (sourceDirValid && outputDirValid
      && binDirValid && srcLanguageValid);
  }
  //#endregion
}
