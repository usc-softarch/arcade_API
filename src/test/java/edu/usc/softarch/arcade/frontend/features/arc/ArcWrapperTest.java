package edu.usc.softarch.arcade.frontend.features.arc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;
import edu.usc.softarch.arcade.frontend.arghandlers.ArcBaseDir;

@RunWith(Parameterized.class)
public class ArcWrapperTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String outputDirectory;
  String classesDirectory;
  String language;
  //#endregion

  //#region CONSTRUCTOR
  public ArcWrapperTest(
    String sourceDirectory, String outputDirectory, String classesDirectory,
    String language)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.outputDirectory = outputDirectory;
    this.classesDirectory = classesDirectory;
    this.language = language;
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
        "target" + fs + "test-results" + fs + "ArcWrapper" + fs + "Struts2",
        "lib_struts", "java"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src",
        "target" + fs + "test-results" + fs + "ArcWrapper" + fs + "httpd",
        "", "c"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testArcWrapper()
    throws Exception
  {
    FeatureWrapper arcWrapper = new ArcWrapper();
    String fs = File.separator;
    SourceDir.getInstance().setValue(sourceDirectory);
    OutputDir.getInstance().setValue(outputDirectory);
    BinDir.getInstance().setValue(classesDirectory);
    SrcLanguage.getInstance().setValue(language);

    //TODO This part is here to make up for the hard-coded Arc implementation.
    //     This test will need to be rewritten after Arc is refactored.
    File arcDir = new File(outputDirectory + fs + "arc" + fs + "base");
    arcDir.getParentFile().mkdirs();
    File projectDir = new File(sourceDirectory);
    projectDir = projectDir.getParentFile();
    String arcBasePath = projectDir.getPath() + fs + "arc" + fs + "base";
    File originalArcBase = new File(arcBasePath);
    FileUtils.copyDirectory(originalArcBase, arcDir);

    try { arcWrapper.execute(); }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  //#endregion
}
