package edu.usc.softarch.arcade.frontend.tooladapters.cvg;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import java.util.Arrays;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.PythonPackage;
import edu.usc.softarch.arcade.frontend.arghandlers.StdOutRedirect;
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;

@RunWith(Parameterized.class)
public class SimEvolAnalyzerWrapperTest
{
  //#region ATTRIBUTES
  String stdOutRedirect;
  String pythonPackage;
  String workingDir;
  String clusterDir;
  //#endregion

  //#region CONSTRUCTOR
  public SimEvolAnalyzerWrapperTest(String stdOutRedirect, String pythonPackage,
    String workingDir, String clusterDir)
  {
    super();
    this.stdOutRedirect = stdOutRedirect;
    this.pythonPackage = pythonPackage;
    this.workingDir = workingDir;
    this.clusterDir = clusterDir;
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
    	  "src" + fs + "main" + fs + "resources" + fs + "root.log",     	        
//        "D:\\arcade_console" + fs + "target" + fs + "test-results" + fs + "SimEvolAnalyzerWrapper" + fs + "Struts2"
//        + fs + "acdc" + fs + "root.log",
        "python.cvg.simevolanalyzer",  
        "src" + fs + "main" + fs + "resources",                
        "D:\\arcade_console" + fs + "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
        + "Struts2" + fs + "acdc" + fs + "cluster"
      },
      
      {
    	  "src" + fs + "main" + fs + "resources" + fs + "root.log", 
    	  "python.cvg.simevolanalyzer",     	  
//          "D:\\arcade_console" + fs + "target" + fs + "test-results" + fs + "SimEvolAnalyzerWrapper" + fs + "Struts2"
//          + fs + "arc" + fs + "root.log",    	  
          "src" + fs + "main" + fs + "resources",                 
          "D:\\arcade_console" + fs + "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "cluster"
      },
      
      {
    	  "src" + fs + "main" + fs + "resources" + fs + "root.log",           
//          "D:\\arcade_console" + fs + "target" + fs + "test-results" + fs + "SimEvolAnalyzerWrapper" + fs + "httpd"
//          + fs + "acdc" + fs + "root.log",    	    
          "python.cvg.simevolanalyzer",       
          "src" + fs + "main" + fs + "resources", 
          "D:\\arcade_console" + fs + "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "cluster"
      },
      
      {
    	  "src" + fs + "main" + fs + "resources" + fs + "root.log",     	  
//    	  "D:\\arcade_console" + fs + "target" + fs + "test-results" + fs + "SimEvolAnalyzerWrapper" + fs + "httpd"
//    	  + fs + "arc" + fs + "root.log",    	  
          "python.cvg.simevolanalyzer",        
          "src" + fs + "main" + fs + "resources", 
          "D:\\arcade_console" + fs + "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc" + fs + "cluster"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testSimEvolAnalyzerWrapper()
  {    
    FeatureWrapper cvg = new SimEvolAnalyzerWrapper();    

    StdOutRedirect.getInstance().setValue(stdOutRedirect);
    PythonPackage.getInstance().setValue(pythonPackage);
    WorkingDir.getInstance().setValue(workingDir);
    ClusterDir.getInstance().setValue(clusterDir);

    try 
    { 
    	cvg.checkArguments(false);
    	cvg.execute(); 
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    
  }
  //#endregion
}
