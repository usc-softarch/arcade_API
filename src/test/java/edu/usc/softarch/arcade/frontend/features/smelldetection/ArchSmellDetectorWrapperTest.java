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
        "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
          + fs + "struts-2.3.30_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.3.32_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster" + fs
          + "struts-2.3.32_acdc_clustered.rsf",
        "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
          + fs + "struts-2.3.32_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "struts-2.5.2_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs
          + "struts-2.5.2_284_topics_275_arc_clusters.rsf",
        "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
          + fs + "struts-2.5.2_smells.ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "struts-2.5.8_deps.rsf",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs
          + "struts-2.5.8_287_topics_279_arc_clusters.rsf",
        "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
          + fs + "struts-2.5.8_smells.ser"
      },
      {
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "Struts2" + fs + "acdc" + fs + "dep" + fs
            + "struts-2.3.30_deps.rsf",
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "Struts2" + fs + "pkg" + fs + "cluster" + fs
            + "struts-2.3.30_deps_pkgs.rsf",
          "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
            + fs + "struts-2.3.30_smells.ser"
        },
        {
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "Struts2" + fs + "acdc" + fs + "dep" + fs
            + "struts-2.3.32_deps.rsf",
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "Struts2" + fs + "pkg" + fs + "cluster" + fs
            + "struts-2.3.32_deps_pkgs.rsf",
          "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
            + fs + "struts-2.3.32_smells.ser"
        },
        {
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "acdc" + fs + "dep" + fs
              + "httpd-2.3.8_deps.rsf",
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "acdc" + fs + "cluster" + fs
              + "httpd-2.3.8_acdc_clustered.rsf",
            "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
              + fs + "httpd-2.3.8_acdc_smells.ser"
        },
        {
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "acdc" + fs + "dep" + fs
              + "httpd-2.4.7_deps.rsf",
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "acdc" + fs + "cluster" + fs
              + "httpd-2.4.7_acdc_clustered.rsf",
            "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
              + fs + "httpd-2.4.7_acdc_smells.ser"
        },
        {
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs + "httpd-2.4.10_deps.rsf",
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs
              + "httpd-2.4.10_46_topics_71_arc_clusters.rsf",
            "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
              + fs + "httpd-2.4.10_smells.ser"
        },
        {
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs + "httpd-2.4.16_deps.rsf",
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs
              + "httpd-2.4.16_46_topics_71_arc_clusters.rsf",
            "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
              + fs + "httpd-2.4.16_smells.ser"
        },
        {
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "acdc" + fs + "dep" + fs
                + "httpd-2.4.26_deps.rsf",
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "pkg" + fs + "cluster" + fs
                + "httpd-2.4.26_deps_pkgs.rsf",
              "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
                + fs + "httpd-2.4.26_deps_pkg_smells.ser"
        },
        {
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "acdc" + fs + "dep" + fs
                + "httpd-2.3.8_deps.rsf",
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "pkg" + fs + "cluster" + fs
                + "httpd-2.3.8_deps_pkgs.rsf",
              "target" + fs + "test-results" + fs + "SmellDetector" + fs + "smells"
                + fs + "httpd-2.3.8_deps_pkg_smells.ser"
        }
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for ArchSmellDetector. Takes the input from four
   * versions of Struts2 and four version of httpd, split between acdc and arc,
   * and generates _smells.ser files from it.
   */
  @Test
  public void testArchSmellDetectorWrapper()
  {
    FeatureWrapper archSmellDetector = new ArchSmellDetectorWrapper();
    DepsRsfFile.getInstance().setValue(depsRsfFile);
    ClusterFile.getInstance().setValue(clusterFile);
    SmellsFile.getInstance().setValue(smellsFile);

    try
    {
      archSmellDetector.checkArguments(false);
      archSmellDetector.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    //TODO Automatically validate that the output is correct.
    File output = new File(smellsFile);
    assert output.exists();
  }
  //#endregion
}
