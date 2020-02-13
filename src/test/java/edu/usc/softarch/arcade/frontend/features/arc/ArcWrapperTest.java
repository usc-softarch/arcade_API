package edu.usc.softarch.arcade.frontend.features.arc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;

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
        "target" + fs + "test-results" + fs + "Struts2", "lib_struts", "java"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src",
        "target" + fs + "test-results" + fs + "httpd", "", "c"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testArcWrapper()
  {
    FeatureWrapper arcWrapper = new ArcWrapper();
    SourceDir.getInstance().setValue(sourceDirectory);
    OutputDir.getInstance().setValue(outputDirectory);
    BinDir.getInstance().setValue(classesDirectory);
    SrcLanguage.getInstance().setValue(language);

    try { arcWrapper.execute(); }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    // String fs = File.separator;
    // File outputDir = new File(outputDirectory);
    // File[] outputFiles = outputDir.listFiles();
    // for(File output : outputFiles)
    // {
    //   if(!output.isDirectory())
    //   {
    //     File oracle = new File("src" + fs + "test" + fs + "resources" + fs
    //       + "subject_systems" + fs + "httpd" + fs + "arc" + fs
    //       + output.getName());
    //
    //     try
    //     {
    //       byte[] f1 = Files.readAllBytes(output.toPath());
    //       byte[] f2 = Files.readAllBytes(oracle.toPath());
    //
    //       assert Arrays.equals(f1, f2);
    //     }
    //     catch(IOException e)
    //     {
    //       Assert.fail(e.getMessage());
    //     }
    //   }
    // }
  }
  //#endregion
}
