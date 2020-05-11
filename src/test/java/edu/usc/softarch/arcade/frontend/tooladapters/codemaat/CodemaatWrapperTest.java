package edu.usc.softarch.arcade.frontend.tooladapters.codemaat;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.GitProjectLog;
import edu.usc.softarch.arcade.frontend.arghandlers.CodeMaatPath;

@RunWith(Parameterized.class)
public class CodemaatWrapperTest
{
  //#region ATTRIBUTES
  String stdOutRedirect;
  String jarFilePath;
  String projectLogFile;
  //#endregion

  //#region CONSTRUCTOR
  public CodemaatWrapperTest(String stdOutRedirect, String jarFilePath, String projectLogFile)
  {
    super();
    this.stdOutRedirect = stdOutRedirect;
    this.jarFilePath = jarFilePath;
    this.projectLogFile = projectLogFile;
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
        "target" + fs + "test-results" + fs + "CodeMaat" + fs + "struts2.csv",
        "src" + fs + "test" + fs + "resources" + fs + "tools" + fs + "code-maat"
          + fs + "code-maat-1.0-SNAPSHOT-standalone.jar",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "struts2.log"
      },
      {
        "target" + fs + "test-results" + fs + "CodeMaat" + fs + "httpd.csv",
        "src" + fs + "test" + fs + "resources" + fs + "tools" + fs + "code-maat"
          + fs + "code-maat-1.0-SNAPSHOT-standalone.jar",
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "git_repo_logs" + fs + "httpd.log"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testCodemaatWrapper()
  {
    FeatureWrapper testcodemaat = new CodemaatWrapper();

    StdOutRedirect.getInstance().setValue(stdOutRedirect);
    GitProjectLog.getInstance().setValue(projectLogFile);
    CodeMaatPath.getInstance().setValue(jarFilePath);

    try
    {
    	testcodemaat.checkArguments(false);
    	testcodemaat.execute();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
  }
  //#endregion
}
