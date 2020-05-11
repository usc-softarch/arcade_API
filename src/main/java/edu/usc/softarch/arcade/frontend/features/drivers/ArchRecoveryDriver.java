package edu.usc.softarch.arcade.frontend.features.drivers;

import java.io.File;
import org.apache.commons.lang3.SystemUtils;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.features.acdc.AcdcDriverSingle;
import edu.usc.softarch.arcade.frontend.features.arc.ArcWrapper;
import edu.usc.softarch.arcade.frontend.features.pipeextractor.PipeExtractorWrapper;
import edu.usc.softarch.arcade.frontend.features.visualization.EvaJsonGenerator;
import edu.usc.softarch.arcade.frontend.tooladapters.mallet.Inferencer;
import edu.usc.softarch.arcade.frontend.tooladapters.mallet.TopicModelGenerator;

import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectName;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectVersion;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ArcBaseDir;
import edu.usc.softarch.arcade.frontend.arghandlers.Topicmodel;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletPath;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;
import edu.usc.softarch.arcade.frontend.arghandlers.InferenceFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.EvaJsonFile;

//TODO This whole class needs to be refactored
public class ArchRecoveryDriver
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public ArchRecoveryDriver()
  {
    String id = "archRecoveryDriver";
    String name = "Architecture Recovery Driver";
    ArgHandler[] requiredArguments =
    {
      SourceDir.getInstance(),
      BinDir.getInstance(),
      OutputDir.getInstance(),
      SrcLanguage.getInstance(),
      ProjectName.getInstance(),
      ProjectVersion.getInstance(),
      MalletHome.getInstance()
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
    String arcBaseDir = OutputDir.getInstance().getValue()
      + fs + "arc" + fs + "base";
    String topicmodelFile = arcBaseDir + fs + "topicmodel.data";
    String inferenceFile = arcBaseDir + fs + "infer.mallet";
    String fileName = ProjectName.getInstance().getValue() + "-"
      + ProjectVersion.getInstance().getValue();
    String binDirPath = SourceDir.getInstance().getValue() + fs
      + fileName + fs + BinDir.getInstance().getValue();
    String malletPathString = MalletHome.getInstance().getValue() + fs;
    String acdcClusterFile = OutputDir.getInstance().getValue()
      + fs + "acdc" + fs + fileName + "_cluster.rsf";
    String evaJsonFile = OutputDir.getInstance().getValue()
      + fs + fileName + "_eva.json";

    if(SystemUtils.IS_OS_WINDOWS)
    {
      malletPathString += "bin" + fs + "mallet.bat";
    }
    else
      malletPathString += "bin" + fs + "mallet";

    Topicmodel.getInstance().setValue(topicmodelFile);
    InferenceFile.getInstance().setValue(inferenceFile);
    ArcBaseDir.getInstance().setValue(arcBaseDir);
    BinDirPath.getInstance().setValue(binDirPath);
    MalletPath.getInstance().setValue(malletPathString);
    ClusterFile.getInstance().setValue(acdcClusterFile);
    EvaJsonFile.getInstance().setValue(evaJsonFile);

    // Setting up Subcomponents
    FeatureWrapper acdc = new AcdcDriverSingle();
    FeatureWrapper pipeExtractor = new PipeExtractorWrapper();
    FeatureWrapper tmg = new TopicModelGenerator();
    FeatureWrapper inferencer = new Inferencer();
    FeatureWrapper arc = new ArcWrapper();
    FeatureWrapper evaJsonGen = new EvaJsonGenerator();

    // ACDC
    acdc.checkArguments(false);
    acdc.execute();

    // PipeExtractor
    pipeExtractor.checkArguments(false);
    pipeExtractor.execute();

    // TopicModelGenerator
    tmg.checkArguments(false);
    tmg.execute();

    // Inferencer
    inferencer.checkArguments(false);
    inferencer.execute();

    // ARC
    arc.checkArguments(false);
    arc.execute();

    // EVA Json
    evaJsonGen.checkArguments(false);
    evaJsonGen.execute();
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean binDirValid = BinDir.getInstance().validateAsInput();
    boolean outputDirValid = OutputDir.getInstance().validateAsOutput();
    boolean srcLanguageValid = SrcLanguage.getInstance().validateAsInput();
    boolean projectNameValid = ProjectName.getInstance().validateAsOutput();
    boolean projectVersionValid =
      ProjectVersion.getInstance().validateAsOutput();
    boolean sourceDirValid = SourceDir.getInstance().validateAsInput();
    boolean malletHomeValid = MalletHome.getInstance().validateAsInput();

    return (outputDirValid && binDirValid && srcLanguageValid
      && projectNameValid && projectVersionValid && sourceDirValid
      && malletHomeValid);
  }
  //#endregion
}
