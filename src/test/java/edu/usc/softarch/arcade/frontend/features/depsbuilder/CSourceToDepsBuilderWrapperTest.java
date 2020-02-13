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
public class CSourceToDepsBuilderWrapperTest
{
  //#region ATTRIBUTES
  String binariesDirectory;
  String depsRsfFile;
  //#endregion

  //#region CONSTRUCTOR
  public CSourceToDepsBuilderWrapperTest(String binariesDirectory,
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
          + "httpd" + fs + "src" + fs + "httpd-2.3.8",
        "target" + fs + "test-results" + fs + "httpd" + fs + "commonRes"
          + fs + "httpd-2.3.8_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.7",
        "target" + fs + "test-results" + fs + "httpd" + fs + "commonRes"
          + fs + "httpd-2.4.7_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.10",
        "target" + fs + "test-results" + fs + "httpd" + fs + "commonRes"
          + fs + "httpd-2.4.10_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.16",
        "target" + fs + "test-results" + fs + "httpd" + fs + "commonRes"
          + fs + "httpd-2.4.16_deps.rsf"
      },
      {
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "src" + fs + "httpd-2.4.26",
        "target" + fs + "test-results" + fs + "httpd" + fs + "commonRes"
          + fs + "httpd-2.4.26_deps.rsf"
      },
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testJavaSourceToDepsBuilderWrapper()
  {
    FeatureWrapper cDepsBuilder = new CSourceToDepsBuilderWrapper();
    BinDirPath.getInstance().setValue(binariesDirectory);
    DepsRsfFile.getInstance().setValue(depsRsfFile);

    try
    {
      cDepsBuilder.checkArguments();
      cDepsBuilder.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    File output = new File(depsRsfFile);
    assert output.exists();
  }
  //#endregion
}
