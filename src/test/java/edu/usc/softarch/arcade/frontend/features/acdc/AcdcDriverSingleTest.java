package edu.usc.softarch.arcade.frontend.features.acdc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectName;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectVersion;

@RunWith(Parameterized.class)
public class AcdcDriverSingleTest
{
  //#region ATTRIBUTES
  String binDirPath;
  String outputDir;
  String srcLanguage;
  String projectName;
  String projectVersion;
  //#endregion

  //#region CONSTRUCTOR
  public AcdcDriverSingleTest(String binDirPath, String outputDir,
    String srcLanguage, String projectName, String projectVersion)
  {
    super();
    this.binDirPath = binDirPath;
    this.outputDir = outputDir;
    this.srcLanguage = srcLanguage;
    this.projectName = projectName;
    this.projectVersion = projectVersion;
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
          + "Struts2" + fs + "src" + fs + "struts-2.3.30" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "Struts2",
        "java", "Struts2", "2.3.30"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.3.32" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "Struts2",
        "java", "Struts2", "2.3.32"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.2" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "Struts2",
        "java", "Struts2", "2.5.2"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.8" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "Struts2",
        "java", "Struts2", "2.5.8"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.10.1" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "Struts2",
        "java", "Struts2", "2.5.10.1"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.3.8" + fs,
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "httpd",
        "c", "httpd", "2.3.8"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.7" + fs,
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "httpd",
        "c", "httpd", "2.4.7"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.10" + fs,
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "httpd",
        "c", "httpd", "2.4.10"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.16" + fs,
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "httpd",
        "c", "httpd", "2.4.16"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.26" + fs,
        "target" + fs + "test-results" + fs + "AcdcDriverSingle"
          + fs + "httpd",
        "c", "httpd", "2.4.26"
      },
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for AcdcDriverSingle. Takes the input from five
   * versions of Struts2 and five versions of httpd from the arcade repository
   * and generates all outputs from the phases of the Acdc workflow.
   *
   * @author Marcelo Schmitt Laser
   */
  @Test
  public void testAcdcDriverSingle()
  {
    FeatureWrapper acdcDriverSingle = new AcdcDriverSingle();
    BinDirPath.getInstance().setValue(binDirPath);
    OutputDir.getInstance().setValue(outputDir);
    SrcLanguage.getInstance().setValue(srcLanguage);
    ProjectName.getInstance().setValue(projectName);
    ProjectVersion.getInstance().setValue(projectVersion);

    try
    {
      acdcDriverSingle.checkArguments(false);
      acdcDriverSingle.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    //TODO validate output
  }
  //#endregion
}
