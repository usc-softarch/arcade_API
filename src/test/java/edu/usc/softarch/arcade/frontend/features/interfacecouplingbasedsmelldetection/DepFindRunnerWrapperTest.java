package edu.usc.softarch.arcade.frontend.features.interfacecouplingbasedsmelldetection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepFinderDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;

@RunWith(Parameterized.class)
public class DepFindRunnerWrapperTest
{
  //#region ATTRIBUTES
  String sourceDir;
  String depFinderDir;
  String binDir;
  //#endregion

  //#region CONSTRUCTOR
  public DepFindRunnerWrapperTest(String sourceDir, String depFinderDir, String binDir)
  {
    super();
    this.sourceDir = sourceDir;   
    this.depFinderDir = depFinderDir; 
    this.binDir = binDir;
  }
  //#endregion

  //#region PARAMETERS
  @Parameterized.Parameters
  public static Collection<Object[]> input()
  {
    String fs = File.separator;
    return Arrays.asList(new Object[][]
    {
//    	only works in Java
//      {
//    	  "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs + "httpd" + fs + "src",
//    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "depfinder" + fs + "httpd",
//    	  ""
//      },
     
      {
    	  "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs + "Struts2" + fs + "src",
    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "depfinder" + fs + "Struts2",
    	  "lib_struts"
      }      
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for DepFindRunner. Takes the input from 
   * Struts2 and httpd source code dir and
   * generates _deps.xml files from it.
   */
  @Test
  public void testDepFindRunnerWrapper()
  {
    FeatureWrapper testDepFindRunner = new DepFindRunnerWrapper();
    SourceDir.getInstance().setValue(sourceDir);    
    DepFinderDir.getInstance().setValue(depFinderDir);  

    try
    {
      testDepFindRunner.checkArguments(false);
      testDepFindRunner.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }

  /**
   * Negative test for DepFindRunnerWrapper. Takes in random, non _deps.xml, Expected output
   * unknown.
   */
  @Test
  public void randomFilesTestDepFindRunner()
  {
    //TODO
  }
  //#endregion
}