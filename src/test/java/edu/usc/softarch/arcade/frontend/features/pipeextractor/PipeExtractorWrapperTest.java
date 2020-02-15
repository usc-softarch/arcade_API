package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ArcBaseDir;

@RunWith(Parameterized.class)
public class PipeExtractorWrapperTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String arcBaseDir;
  //#endregion

  //#region CONSTRUCTOR
  public PipeExtractorWrapperTest(
    String sourceDirectory, String arcBaseDir)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.arcBaseDir = arcBaseDir;
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
          + "Struts2" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "Struts2" + fs + "arc"
          + fs + "base"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "httpd" + fs + "arc"
          + fs + "base"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testPipeExtractorWrapper()
  {
    FeatureWrapper pipeExtractor = new PipeExtractorWrapper();
    SourceDir.getInstance().setValue(sourceDirectory);
    ArcBaseDir.getInstance().setValue(arcBaseDir);

    try
    {
      pipeExtractor.checkArguments(false);
      pipeExtractor.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    String fs = File.separator;
    File output = new File(arcBaseDir + fs + "output.pipe");
    assert output.exists();
  }
  //#endregion
}
