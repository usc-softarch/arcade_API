package edu.usc.softarch.arcade.frontend.features.acdc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;

@RunWith(Parameterized.class)
public class AcdcWrapperTest
{
  //#region ATTRIBUTES
  String depsRsfFile;
  String clusterFile;
  //#endregion

  //#region CONSTRUCTOR
  public AcdcWrapperTest(String depsRsfFile, String clusterFile)
  {
    super();
    this.depsRsfFile = depsRsfFile;
    this.clusterFile = clusterFile;
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
        "target" + fs + "test-results" + fs + "AcdcWrapper" + fs + "acdc" + fs
          + "struts-2.3.30_acdc_clustered.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.3.32_deps.rsf",
        "target" + fs + "test-results" + fs + "AcdcWrapper" + fs + "acdc" + fs
          + "struts-2.3.32_acdc_clustered.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.5.2_deps.rsf",
        "target" + fs + "test-results" + fs + "AcdcWrapper" + fs + "acdc" + fs
          + "struts-2.5.2_acdc_clustered.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.5.8_deps.rsf",
        "target" + fs + "test-results" + fs + "AcdcWrapper" + fs + "acdc" + fs
          + "struts-2.5.8_acdc_clustered.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep" + fs
          + "struts-2.5.10.1_deps.rsf",
        "target" + fs + "test-results" + fs + "AcdcWrapper" + fs + "acdc" + fs
          + "struts-2.5.10.1_acdc_clustered.rsf"
      }
      //TODO add httpd tests
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for ACDC. Takes the input from five versions of
   * Struts2 and five versions of httpd from the arcade repository and
   * generates _acdc_clustered.rsf files from it.
   */
  @Test
  public void testAcdcWrapper()
  {
    FeatureWrapper archSmellDetector = new AcdcWrapper();
    DepsRsfFile.getInstance().setValue(depsRsfFile);
    ClusterFile.getInstance().setValue(clusterFile);

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
    File output = new File(clusterFile);
    assert output.exists();
  }

  /**
   * Negative test for ACDC. Takes in random, non-rsf input. Expected output
   * unknown.
   */
  @Test
  public void randomFilesTestAcdc()
  {
    //TODO
  }
  //#endregion
}
