package edu.usc.softarch.arcade.frontend.features.drivers;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectName;
import edu.usc.softarch.arcade.frontend.arghandlers.ProjectVersion;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;

@RunWith(Parameterized.class)
public class ArchRecoveryDriverTest
{
  //#region ATTRIBUTES
  String sourceDir;
  String binDir;
  String outputDir;
  String srcLanguage;
  String projectName;
  String projectVersion;
  String malletHome;
  //#endregion

  //#region CONSTRUCTOR
  public ArchRecoveryDriverTest(String sourceDir, String binDir, String outputDir,
    String srcLanguage, String projectName, String projectVersion,
    String malletHome)
  {
    super();
    this.sourceDir = sourceDir;
    this.binDir = binDir;
    this.outputDir = outputDir;
    this.srcLanguage = srcLanguage;
    this.projectName = projectName;
    this.projectVersion = projectVersion;
    this.malletHome = malletHome;
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
          + "Struts2" + fs + "src",
        "lib_struts",
        "target" + fs + "test-results" + fs + "ArchRecoveryDriver" + fs
          + "Struts2" + fs + "struts-2.3.30",
        "java", "struts", "2.3.30",
        System.getProperty("user.dir") + fs + "src" + fs
          + "test" + fs + "resources" + fs + "tools" + fs + "mallet-2.0.7" + fs
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testARD()
  {
    FeatureWrapper ard = new ArchRecoveryDriver();

    SourceDir.getInstance().setValue(sourceDir);
    BinDir.getInstance().setValue(binDir);
    OutputDir.getInstance().setValue(outputDir);
    SrcLanguage.getInstance().setValue(srcLanguage);
    ProjectName.getInstance().setValue(projectName);
    ProjectVersion.getInstance().setValue(projectVersion);
    MalletHome.getInstance().setValue(malletHome);

    try
    {
      ard.checkArguments(false);
      ard.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      Assert.fail();
    }
  }
  //#endregion
}
