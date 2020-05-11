package edu.usc.softarch.arcade.frontend.features.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

@RunWith(Parameterized.class)
public class BatchDecayMetricsAnalyzerWrapperTest
{
  //#region ATTRIBUTES
  String clusterDirectory;
  String depsDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public BatchDecayMetricsAnalyzerWrapperTest(String depsDirectory,
    String clusterDirectory)
  {
    super();
    this.depsDirectory = depsDirectory;
    this.clusterDirectory = clusterDirectory;
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
          + "Struts2" + fs + "acdc" + fs + "dep",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "cluster"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "acdc" + fs + "dep",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "cluster"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "dep",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "dep",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "cluster"
      }
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for decay metrics. Takes the dependency and cluster
   * files from five versions of Struts2 and httpd and generates decay metrics
   * analysis output. Total of 6 tests.
   *
   * @author Khoi
   */
  @Test
  public void testBatchDecayMetricsAnalyzerWrapper()
  {
    FeatureWrapper batchDecayMetricsAnalyzer =
      new BatchDecayMetricsAnalyzerWrapper();
    DepsDir.getInstance().setValue(depsDirectory);
    ClusterDir.getInstance().setValue(clusterDirectory);

    try
    {
    	batchDecayMetricsAnalyzer.checkArguments(false);
    	batchDecayMetricsAnalyzer.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // /**
  //  * Negative test for decay metrics analyzer. Takes in random, non-rsf input.
  //  * Expected output unknown.
  //  *
  //  * @author Khoi
  //  */
  // @Test
  // public void randomFilesTestDecayMetrics()
  // {
  //   //TODO
  // }
  //#endregion
}
