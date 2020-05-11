package edu.usc.softarch.arcade.frontend.features.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.CVGResultsFile;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;

@RunWith(Parameterized.class)
public class C2CAverageAnalyzeWrapperTest
{
  //#region ATTRIBUTES
  String cvgResultsFile;
  String stdOutFile;
  //#endregion

  //#region CONSTRUCTOR
  public C2CAverageAnalyzeWrapperTest(String cvgResultsFile, String stdOutFile)
  {
    super();
    this.cvgResultsFile = cvgResultsFile;
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
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "Struts2" + fs
          + "acdc" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "Struts2"
          + fs + "acdc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "Struts2" + fs
          + "arc" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "Struts2"
          + fs + "arc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "Struts2" + fs
          + "pkg" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "Struts2"
          + fs + "pkg.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "httpd" + fs
          + "acdc" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "httpd"
          + fs + "acdc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "httpd" + fs
          + "arc" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "httpd"
          + fs + "arc.txt"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "Simevolanalyzer" + fs + "httpd" + fs
          + "pkg" + fs + "root.log",
        "target" + fs + "test-results" + fs + "C2CAverages" + fs + "httpd"
          + fs + "pkg.txt"
      }
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for a2a. Takes the cluster rsf files from five versions of
   * Struts2 and five versions of httpd from the arcade repository and
   * generates c2c architectural change analysis output.
   */
  @Test
  public void testC2CAverageAnalyzeWrapper()
  {
    FeatureWrapper cvg = new C2CAverageAnalyzeWrapper();
    CVGResultsFile.getInstance().setValue(cvgResultsFile);
    StdOutRedirect.getInstance().setValue(stdOutFile);

    try
    {
      cvg.checkArguments(false);
      cvg.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  //#endregion

  // /**
  //  * Negative test for c2c. Takes in random, non-rsf input. Expected output
  //  * unknown.
  //  */
  // @Test
  // public void randomFilesTestC2c()
  // {
  //   //TODO
  // }
  //#endregion
}
