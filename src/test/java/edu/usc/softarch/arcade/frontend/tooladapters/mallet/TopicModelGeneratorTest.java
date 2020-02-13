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
import edu.usc.softarch.arcade.frontend.arghandlers.Topicmodel;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletPath;
import edu.usc.softarch.arcade.frontend.arghandlers.MalletHome;

@RunWith(Parameterized.class)
public class TopicModelGeneratorTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String topicmodelFile;
  String malletHomeString;
  //#endregion

  //#region CONSTRUCTOR
  public TopicModelGeneratorTest(String sourceDirectory, String topicmodelFile,
    String malletHomeString)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.topicmodelFile = topicmodelFile;
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
        "target" + fs + "test-results" + fs + "httpd" + fs + "arc" + fs
            + "base" + fs + "topicmodel.data",
        System.getProperty("user.dir") + fs + "src" + fs
          + "test" + fs + "resources" + fs + "tools" + fs + "mallet-2.0.7" + fs
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems"
          + fs + "Struts2" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "Struts2" + fs + "arc" + fs
          + "base" + fs + "topicmodel.data",
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
    Topicmodel.getInstance().setValue(topicmodelFile);
    MalletHome.getInstance().setValue(malletHomeString);
    MalletPath.getInstance().setValue(malletPathString);

    try { tmgAdapter.execute(); }
    catch(Exception e)
    {
      Assert.fail(e.getStackTrace().toString());
    }

    File output = new File(topicmodelFile);
    assert output.exists();
  }
  //#endregion
}
