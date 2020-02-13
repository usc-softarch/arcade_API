package edu.usc.softarch.arcade.frontend.tooladapters.mallet;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletPath;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;

@RunWith(Parameterized.class)
public class TopicModelGeneratorTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String outputDirectory;
  String malletHomeString;
  //#endregion

  //#region CONSTRUCTOR
  public TopicModelGeneratorTest(String sourceDirectory, String outputDirectory,
    String malletHomeString)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.outputDirectory = outputDirectory;
    this.malletHomeString = malletHomeString;
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
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems"
          + fs + "httpd" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "Arc"
          + fs + "httpd" + fs + "base" + fs + "topicmodel.data",
        System.getProperty("user.dir") + fs + "src" + fs
          + "test" + fs + "resources" + fs + "tools" + fs + "mallet-2.0.7" + fs
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems"
          + fs + "Struts2" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "Arc"
          + fs + "Struts2" + fs + "base" + fs + "topicmodel.data",
        System.getProperty("user.dir") + fs + "src" + fs
          + "test" + fs + "resources" + fs + "tools" + fs + "mallet-2.0.7" + fs
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testTMG()
  {
    String fs = File.separator;
    String malletPathString = malletHomeString;
    FeatureWrapper tmgAdapter = new TopicModelGenerator();

    if(SystemUtils.IS_OS_LINUX)
    {
      malletPathString += "bin" + fs + "mallet";
    }
    else if(SystemUtils.IS_OS_WINDOWS)
    {
      malletPathString += "bin" + fs + "mallet.bat";
    }
    else
      throw new UnsupportedOperationException("OS unknown.");

    SourceDir.getInstance().setValue(sourceDirectory);
    OutputDir.getInstance().setValue(outputDirectory);
    MalletHome.getInstance().setValue(malletHomeString);
    MalletPath.getInstance().setValue(malletPathString);

    try { tmgAdapter.execute(); }
    catch(Exception e)
    {
      Assert.fail(e.getStackTrace().toString());
    }

    File output = new File(outputDirectory.split(" ")[1]);
    assert output.exists();

    // THIS TEST ALWAYS FAILS BECAUSE OF MALLET NON-DETERMINISM
    // File oracle = new File("src" + fs + "test" + fs + "resources" + fs
    //   + "subject_systems" + fs + "httpd" + fs + "arc" + fs + "base" + fs
    //   + "topicmodel.data");
    //
    // try
    // {
    //   byte[] f1 = Files.readAllBytes(output.toPath());
    //   byte[] f2 = Files.readAllBytes(oracle.toPath());
    //
    //   assert Arrays.equals(f1, f2);
    // }
    // catch(IOException e)
    // {
    //   Assert.fail(e.getMessage());
    // }
  }
  //#endregion
}
