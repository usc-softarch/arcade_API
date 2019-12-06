package edu.usc.softarch.arcade.frontend.features.arc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;

@RunWith(Parameterized.class)
public class ArcWrapperTest
{
  //#region ATTRIBUTES
  String sourceDirectory;
  String outputDirectory;
  String classesDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public ArcWrapperTest(
    String sourceDirectory, String outputDirectory, String classesDirectory)
  {
    super();
    this.sourceDirectory = sourceDirectory;
    this.outputDirectory = outputDirectory;
    this.classesDirectory = classesDirectory;
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
        "lib_struts"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testArcWrapper()
  {
    FeatureWrapper arcWrapper = new ArcWrapper();
    Object[] args = new Object[] { sourceDirectory, outputDirectory,
      classesDirectory };
    try { arcWrapper.execute(args); }
    catch(Exception e)
    {
      //TODO properly handle this
      e.printStackTrace();
    }
    //TODO assert whether the result of execution is correct
  }
  //#endregion
}
