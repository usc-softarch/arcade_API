package edu.usc.softarch.arcade.frontend.features.interfacecouplingbasedsmelldetection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.CloneDir;

@RunWith(Parameterized.class)
public class BatchCloneFinderWrapperTest
{
  //#region ATTRIBUTES
  String sourceDir;
  String cloneDir;
  //#endregion

  //#region CONSTRUCTOR
  public BatchCloneFinderWrapperTest(String sourceDir, String cloneDir)
  {
    super();
    this.sourceDir = sourceDir;   
    this.cloneDir = cloneDir;   
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
    	  "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs + "httpd" + fs + "src",
    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "clone" + fs + "httpd"
      },
     
      {
    	  "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs + "Struts2" + fs + "src",
    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "clone" + fs + "Struts2"
      }      
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for BatchCloneFinder. Takes the input from 
   * Struts2 and httpd source code dir and
   * generates _clone.xml files from it.
   */
  @Test
  public void testBatchCloneFinderWrapper()
  {
    FeatureWrapper testBatchCloneFinder = new BatchCloneFinderWrapper();
    SourceDir.getInstance().setValue(sourceDir);    
    CloneDir.getInstance().setValue(cloneDir);  

    try
    {
      testBatchCloneFinder.checkArguments(false);
      testBatchCloneFinder.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }

  /**
   * Negative test for BatchCloneFinderWrapper. Takes in random, non _clone.xml, Expected output
   * unknown.
   */
  @Test
  public void randomFilesTestBatchCloneFinder()
  {
    //TODO
  }
  //#endregion
}