package edu.usc.softarch.arcade.frontend.features.smellanalysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SmellsDir;

@RunWith(Parameterized.class)
public class SmellEvolutionAnalyzerWrapperTest
{
  //#region ATTRIBUTES
  String smellsDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public SmellEvolutionAnalyzerWrapperTest(String smellsDirectory)
  {
    super();
    this.smellsDirectory = smellsDirectory;
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
          + "Struts2" + fs + "acdc" + fs + "ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "pkg" + fs + "ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "ser"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "pkg" + fs + "ser"
      }
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for Semll Evolution Analyzer. Takes the smell ser file from
   * five versions of Struts2 and five versions of httpd from the arcade repository and
   * generates smell evolution analysis output.
   */
  @Test
  public void testSmellEvolutionAnalyzerWrapper()
  {
    FeatureWrapper smellEvolutionAnalyzer = new SmellEvolutionAnalyzerWrapper();
    SmellsDir.getInstance().setValue(smellsDirectory);

    try
    {
    	smellEvolutionAnalyzer.checkArguments(false);
    	smellEvolutionAnalyzer.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // /**
  //  * Negative test for Semll Evolution Analyzer. Takes in random, non-rsf input. Expected output
  //  * unknown.
  //  */
  // @Test
  // public void randomFilesTestAcdc()
  // {
  //   //TODO
  // }
  //#endregion
}
