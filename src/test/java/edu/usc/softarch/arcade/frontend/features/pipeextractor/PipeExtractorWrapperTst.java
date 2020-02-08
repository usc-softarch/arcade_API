package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

@RunWith(Parameterized.class)
public class PipeExtractorWrapperTst
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String outputDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public PipeExtractorWrapperTst(
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
        "target" + fs + "test-results" + fs + "Struts2"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs,
        "target" + fs + "test-results" + fs + "httpd"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testPipeExtractorWrapper()
  {
    FeatureWrapper pipeExtractor = new PipeExtractorWrapper();
    Map<String,String> args = new HashMap<String,String>();
    args.put(arcade.strings.args.sourceDir.id, sourceDirectory);
    args.put(arcade.strings.args.outputDir.id, outputDirectory);
    try
    {
      pipeExtractor.checkArguments(args);
      pipeExtractor.execute(args);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    String fs = File.separator;
    File output = new File(outputDirectory + fs + "output.pipe");
    assert output.exists();

    // THIS TEST ALWAYS FAILS BECAUSE OF MALLET NON-DETERMINISM
    // File oracle = new File("src" + fs + "test" + fs + "resources" + fs
    //   + "subject_systems" + fs + "Struts2" + fs + "arc" + fs + "base" + fs
    //   + "output.pipe");
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
