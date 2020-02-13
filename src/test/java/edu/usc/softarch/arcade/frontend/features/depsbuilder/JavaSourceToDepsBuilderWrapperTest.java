package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDirPath;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsRsfFile;

@RunWith(Parameterized.class)
public class JavaSourceToDepsBuilderWrapperTest
{
  //#region ATTRIBUTES
  String binariesDirectory;
  String depsRsfFile;
  //#endregion

  //#region CONSTRUCTOR
  public JavaSourceToDepsBuilderWrapperTest(String binariesDirectory,
    String depsRsfFile)
  {
    super();
    this.binariesDirectory = binariesDirectory;
    this.depsRsfFile = depsRsfFile;
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
          + "Struts2" + fs + "src" + fs + "struts-2.3.30" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "Struts2" + fs + "commonRes"
          + fs + "struts-2.3.30_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.3.32" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "Struts2" + fs + "commonRes"
          + fs + "struts-2.3.32_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.2" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "Struts2" + fs + "commonRes"
          + fs + "struts-2.5.2_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.8" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "Struts2" + fs + "commonRes"
          + fs + "struts-2.5.8_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "src" + fs + "struts-2.5.10.1" + fs + "lib_struts",
        "target" + fs + "test-results" + fs + "Struts2" + fs + "commonRes"
          + fs + "struts-2.5.10.1_deps.rsf"
      },
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for Java depsbuilder. Takes the input from five
   * versions of Struts2 from the arcade repository and generates _deps.rsf
   * files from it.
   */
  @Test
  public void baseTestJavaDepsBuilder()
  {
    FeatureWrapper javaDepsBuilder = new JavaSourceToDepsBuilderWrapper();
    BinDirPath.getInstance().setValue(binariesDirectory);
    DepsRsfFile.getInstance().setValue(depsRsfFile);

    try
    {
      javaDepsBuilder.checkArguments(false);
      javaDepsBuilder.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    //TODO Automatically validate that the output is correct.
    File output = new File(depsRsfFile);
    assert output.exists();
  }

  /**
   * Negative test for Java depsbuilder. Takes in input from languages other
   * than Java. Expected output unknown.
   */
  @Test
  public void wrongLanguageTestJavaDepsBuilder()
  {
    //TODO
  }

  /**
   * Negative test for Java depsbuilder. Takes in random input. Expected
   * output unknown.
   */
  @Test
  public void randomFilesTestJavaDepsBuilder()
  {
   //TODO
  }
  //#endregion
}
