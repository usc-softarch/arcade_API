package edu.usc.softarch.arcade.frontend.features.a2a;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

@RunWith(Parameterized.class)
public class BatchSystemEvoWrapperTest
{
  //#region ATTRIBUTES  
  String clusterDirectory;
  String distOptVal;
  //#endregion

  //#region CONSTRUCTOR
  public BatchSystemEvoWrapperTest(String clusterDirectory, String distOptVal)
  {
    super();    
    this.clusterDirectory = clusterDirectory;
    this.distOptVal = distOptVal;
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
            + "Struts2" + fs + "acdc" + fs + "cluster", "1"
      },
      
      {
        	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "Struts2" + fs + "acdc" + fs + "cluster", "2" 
      },   
      
      {          
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "Struts2" + fs + "acdc" + fs + "cluster", "3" 
      },
    
      {         
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "Struts2" + fs + "arc" + fs + "cluster", "1" 
      },
        
      {
          	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "Struts2" + fs + "arc" + fs + "cluster", "2" 
      },   
        
      {            
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "Struts2" + fs + "arc" + fs + "cluster", "3" 
      },
      
      {          
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "Struts2" + fs + "pkg" + fs + "cluster", "1" 
      },
        
      {
          	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "Struts2" + fs + "pkg" + fs + "cluster", "2" 
      },   
        
      {           
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "Struts2" + fs + "pkg" + fs + "cluster", "3" 
      },
        
      {        
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "httpd" + fs + "acdc" + fs + "cluster", "1" 
      },
      
      {
        	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
            + "httpd" + fs + "acdc" + fs + "cluster", "2" 
      },   
      
      {
          
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "acdc" + fs + "cluster", "3" 
      },
    
      {          
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs + "cluster", "1" 
      },
        
      {
          	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "arc" + fs + "cluster", "2" 
      },   
        
      {           
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "arc" + fs + "cluster", "3" 
      },
      
      {          
            "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "pkg" + fs + "cluster", "1" 
      },
        
      {
          	"src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
              + "httpd" + fs + "pkg" + fs + "cluster", "2" 
      },   
        
      {            
              "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
                + "httpd" + fs + "pkg" + fs + "cluster", "3" 
      }
    });
  }
  //#endregion


  //#region TESTS
  /**
   * Basic positive test for a2a. Takes the cluster rsf files from five versions of
   * Struts2 and five versions of httpd from the arcade repository and
   * generates a2a architectural change analysis output.
   */
  @Test
  public void testBatchSystemEvoWrapper()
  {
    FeatureWrapper a2a = new BatchSystemEvoWrapper();
    DistOpt.getInstance().setValue(distOptVal);
    ClusterDir.getInstance().setValue(clusterDirectory);

    try
    {
      a2a.checkArguments(false);
      a2a.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }
  //#endregion
  
  /**
   * Negative test for a2a. Takes in random, non-rsf input. Expected output
   * unknown.
   */
  @Test
  public void randomFilesTestA2a()
  {
    //TODO
  }
  //#endregion
}
