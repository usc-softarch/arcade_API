package edu.usc.softarch.arcade.frontend.features.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
import edu.usc.softarch.arcade.frontend.arghandlers.A2AResult;

@RunWith(Parameterized.class)
public class BatchSystemEvoWrapperTest
{
  //#region ATTRIBUTES
  String distOptVal;
  String clusterDirectory;
  String a2aResult;
  //#endregion

  //#region CONSTRUCTOR
  public BatchSystemEvoWrapperTest(String clusterDirectory, String distOptVal,
    String a2aResult)
  {
    super();
    this.clusterDirectory = clusterDirectory;
    this.distOptVal = distOptVal;
    this.a2aResult = a2aResult;
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
          + "Struts2" + fs + "acdc" + fs + "cluster", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "acdc_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "acdc_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "acdc_3.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "arc_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "arc_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "arc_3.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "cluster", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "pkg_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "cluster", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "pkg_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "cluster", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "Struts2" + fs
          + "pkg_3.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "acdc_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "acdc_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "acdc_3.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "arc_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "arc_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "arc_3.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "cluster", "1",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "pkg_1.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "cluster", "2",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "pkg_2.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "cluster", "3",
        "target" + fs + "test-results" + fs + "A2A" + fs + "httpd" + fs
          + "pkg_3.txt"
      }
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for a2a. Takes the cluster rsf files from five versions
   * of Struts2 and five versions of httpd from the arcade repository and
   * generates a2a architectural change analysis output. Total of 18 tests,
   * with combinations of clustering results from ACDC, ARC and PKG, using
   * DistOpt values of 1, 2 or 3.
   *
   * @author Khoi
   */
  @Test
  public void testBatchSystemEvoWrapper()
  {
    FeatureWrapper a2a = new BatchSystemEvoWrapper();
    DistOpt.getInstance().setValue(distOptVal);
    ClusterDir.getInstance().setValue(clusterDirectory);
    A2AResult.getInstance().setValue(a2aResult);

    try
    {
      a2a.checkArguments(false);
      a2a.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // /**
  //  * Negative test for a2a. Takes in random, non-rsf input. Expected output
  //  * unknown.
  //  *
  //  * @author Khoi
  //  */
  // @Test
  // public void randomFilesTestA2a()
  // {
  //   //TODO
  // }
  //#endregion
}
