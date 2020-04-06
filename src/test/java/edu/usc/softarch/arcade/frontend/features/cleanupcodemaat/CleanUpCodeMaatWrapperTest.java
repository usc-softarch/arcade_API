package edu.usc.softarch.arcade.frontend.features.cleanupcodemaat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.CodemaatDir;

@RunWith(Parameterized.class)
public class CleanUpCodeMaatWrapperTest
{
  //#region ATTRIBUTES
  String codemaatDir;  
  //#endregion

  //#region CONSTRUCTOR
  public CleanUpCodeMaatWrapperTest(String codemaatDir)
  {
    super();
    this.codemaatDir = codemaatDir;    
  }
  //#endregion

  //#region PARAMETERS
  @Parameterized.Parameters
  public static Collection<Object[]> input()
  {
    String fs = File.separator;
    return Arrays.asList(new Object[][]
    {
//    	clean up prefix pkg only for Java
//      {
//    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "httpd" + fs + "httpd.csv"
//      },
     
      {
    	  "src" + fs + "test" + fs + "resources" + fs + "git_repo_logs" + fs + "Struts2"// + fs + "struts2.csv"
      }      
    });
  }
  //#endregion

  //#region TESTS
  /**
   * Basic positive test for CleanUpCodeMaat. Takes the input from 
   * Struts2 and httpd CodeMaat dir (.csv file) and
   * generates clean.csv files from it.
   */
  @Test
  public void testCleanUpCodeMaatWrapper()
  {
    FeatureWrapper testCleanUpCodeMaat = new CleanUpCodeMaatWrapper();
    CodemaatDir.getInstance().setValue(codemaatDir);    

    try
    {
      testCleanUpCodeMaat.checkArguments(false);
      testCleanUpCodeMaat.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }

  /**
   * Negative test for CleanUpCodeMaatWrapper. Takes in random, non csv file, Expected output
   * unknown.
   */
  @Test
  public void randomFilesTestCleanUpCodeMaat()
  {
    //TODO
  }
  //#endregion
}