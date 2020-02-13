package edu.usc.softarch.arcade.frontend.features.smelldetection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsFile;

@RunWith(Parameterized.class)
public class ArchSmellDetectorWrapperTest
{
  //#region ATTRIBUTES
  String depsRsfFile;
  String clusterFile;
  String smellsFile;
  //#endregion

  //#region CONSTRUCTOR
  public ArchSmellDetectorWrapperTest(String depsRsfFile,
    String clusterFile, String smellsFile)
  {
    super();
    this.depsRsfFile = depsRsfFile;
    this.clusterFile = clusterFile;
    this.smellsFile = smellsFile;
  }
  //#endregion

  //#region PARAMETERS
  @Parameterized.Parameters
  public static Collection<Object[]> input()
  {
    String fs = File.separator;
    return Arrays.asList(new Object[][]
    {
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.3.30_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster" + fs
          + "struts-2.3.30_acdc_clustered.rsf",
        "target" + fs + "test-results" + fs + "smells" + fs
          + "struts-2.3.30_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.3.32_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster" + fs
          + "struts-2.3.32_acdc_clustered.rsf",
        "target" + fs + "test-results" + fs + "smells" + fs
          + "struts-2.3.32_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "struts-2.5.2_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs
          + "struts-2.5.2_284_topics_275_arc_clusters.rsf",
        "target" + fs + "test-results" + fs + "smells" + fs
          + "struts-2.5.2_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "struts-2.5.8_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs
          + "struts-2.5.8_287_topics_279_arc_clusters.rsf",
        "target" + fs + "test-results" + fs + "smells" + fs
          + "struts-2.5.8_smells.ser"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testArchSmellDetectorWrapper()
  {
    FeatureWrapper archSmellDetector = new ArchSmellDetectorWrapper();
    DepsRsfFile.getInstance().setValue(depsRsfFile);
    ClusterFile.getInstance().setValue(clusterFile);
    SmellsFile.getInstance().setValue(smellsFile);

    try
    {
      archSmellDetector.checkArguments();
      archSmellDetector.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    File output = new File(smellsFile);
    assert output.exists();
  }
  //#endregion
}
