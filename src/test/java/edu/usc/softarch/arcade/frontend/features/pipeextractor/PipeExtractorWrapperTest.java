package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;

@RunWith(Parameterized.class)
public class PipeExtractorWrapperTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String outputDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public PipeExtractorWrapperTest(
    String sourceDirectory, String outputDirectory)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.outputDirectory = outputDirectory;
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
        "target" + fs + "test-results" + fs + "PipeExtractor" + fs + "Struts2"
          + fs
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testPipeExtractorWrapper()
  {
    FeatureWrapper pipeExtractor = new PipeExtractorWrapper();
    Object[] args = new Object[] { sourceDirectory, outputDirectory };
    try { pipeExtractor.execute(args); }
    catch(Exception e)
    {
      //TODO properly handle this
      e.printStackTrace();
    }
    //TODO assert whether the result of execution is correct
  }
  //#endregion
}
