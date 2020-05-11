package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;

@RunWith(Parameterized.class)
public class SmellDensityAnalyzerWrapperTest
{
  //#region ATTRIBUTES
  String clusterDirectory;
  String smellsDirectory;
  String stdOutFile;
  //#endregion

  //#region CONSTRUCTOR
  public SmellDensityAnalyzerWrapperTest(String smellsDirectory,
    String clusterDirectory, String stdOutFile)
  {
    super();
    this.smellsDirectory = smellsDirectory;
    this.clusterDirectory = clusterDirectory;
    this.stdOutFile = stdOutFile;
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
          + "Struts2" + fs + "acdc" + fs + "ser",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "Struts2" + fs + "acdc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "Struts2" + fs + "arc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "ser",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "cluster",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "Struts2" + fs + "pkg.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "ser",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "httpd" + fs + "acdc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "httpd" + fs + "arc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "ser",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "cluster",
        "target" + fs + "test-results" + fs + "SmellDensityAnalyzer" + fs
          + "httpd" + fs + "pkg.txt"
      }
    });
  }
  //#endregion


  //#region TESTS
  /**
   * Basic positive test for Semll Density Analyzer. Takes the cluster rsf files and smell ser file
   * from five versions of Struts2 and five versions of httpd from the arcade repository and
   * generates smell sensity analysis output.
   */
  @Test
  public void testSmellDensityAnalyzerWrapper()
  {
    FeatureWrapper smellDensityAnalyzer = new SmellDensityAnalyzerWrapper();
    SmellsDir.getInstance().setValue(smellsDirectory);
    ClusterDir.getInstance().setValue(clusterDirectory);
    StdOutRedirect.getInstance().setValue(stdOutFile);

    try
    {
    	smellDensityAnalyzer.checkArguments(false);
    	smellDensityAnalyzer.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // /**
  //  * Negative test for Smell Density Analyzer. Takes in random, non-rsf input. Expected output
  //  * unknown.
  //  */
  // @Test
  // public void randomFilesTestAcdc()
  // {
  //   //TODO
  // }
  //#endregion
}
