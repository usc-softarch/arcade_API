package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.File;
import java.lang.String;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.acdc.AcdcWrapper;
import edu.usc.softarch.arcade.frontend.features.smelldetection.ArchSmellDetectorWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.JavaSourceToDepsBuilderWrapper;
import edu.usc.softarch.arcade.frontend.features.depsbuilder.CSourceToDepsBuilderWrapper;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectName;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectVersion;

public class AcdcDriverSingle
  extends FeatureWrapper
{
  //#region CONSTRUCTOR
  public AcdcDriverSingle()
  {
    String id = "acdcDriverSingle";
    String name = "ACDC Workflow Driver: Single Version";
    ArgHandler[] requiredArguments =
    {
      BinDirPath.getInstance(),
      OutputDir.getInstance(),
      SrcLanguage.getInstance(),
      ProjectName.getInstance(),
      ProjectVersion.getInstance()
    };
    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    // Setting up Arguments
    String fs = File.separator;
    String outputDirPath = OutputDir.getInstance().getValue() + fs;
    String srcLanguage = SrcLanguage.getInstance().getValue().toLowerCase();
    String fileName = ProjectName.getInstance().getValue() + "-";
    fileName += ProjectVersion.getInstance().getValue();
    FeatureWrapper acdc = new AcdcWrapper();
    FeatureWrapper archsmelldetector = new ArchSmellDetectorWrapper();
    FeatureWrapper depsbuilder;

    String depsRsfFileVal = outputDirPath + "commonRes"
      + fs + fileName + "_deps.rsf";
    DepsRsfFile.getInstance().setValue(depsRsfFileVal);
    String clusterFileVal = outputDirPath + fs + "acdc"
      + fs + fileName + "_cluster.rsf";
    ClusterFile.getInstance().setValue(clusterFileVal);
    String smellsFileVal = outputDirPath + fs + "acdc"
      + fs + fileName + "_smells.ser";
    SmellsFile.getInstance().setValue(smellsFileVal);

    // Dependency Builder
    switch(srcLanguage)
    {
      case "java":
        depsbuilder = new JavaSourceToDepsBuilderWrapper();
        break;
      case "c":
        depsbuilder = new CSourceToDepsBuilderWrapper();
        break;
      default:
        throw new Exception("Unrecognized source language.");
    }
    depsbuilder.checkArguments(false);
    depsbuilder.execute();

    // Architectural Recovery
    acdc.checkArguments(false);
    acdc.execute();

    // Smell Detection
    archsmelldetector.checkArguments(false);
    archsmelldetector.execute();
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirPathValid = BinDirPath.getInstance().validateAsInput();
    boolean outputDirValid = OutputDir.getInstance().validateAsOutput();
    boolean srcLanguageValid = SrcLanguage.getInstance().validateAsInput();
    boolean projectNameValid = ProjectName.getInstance().validateAsOutput();
    boolean projectVersionValid =
      ProjectVersion.getInstance().validateAsOutput();

    return (outputDirValid && binDirPathValid && srcLanguageValid
      && projectNameValid && projectVersionValid);
  }
  //#endregion
}
