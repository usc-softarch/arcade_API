package edu.usc.softarch.arcade.frontend.tooladapters.pkg;

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
import edu.usc.softarch.arcade.frontend.arghandlers.WorkingDir;
import edu.usc.softarch.arcade.frontend.arghandlers.DepsDir;
import edu.usc.softarch.arcade.frontend.arghandlers.PkgPrefixes;


@RunWith(Parameterized.class)
public class BatchPackagerWrapperTest
{
  //#region ATTRIBUTES
  String depsDir;
  String pythonPackage;
  String workingDir;
  String pkgPrefixes;
  //#endregion

  //#region CONSTRUCTOR
  public BatchPackagerWrapperTest(String workingDir, String pythonPackage,
		  String depsDir, String pkgPrefixes)
  {
    super();
    this.depsDir = depsDir;
    this.pythonPackage = pythonPackage;
    this.workingDir = workingDir;
    this.pkgPrefixes = pkgPrefixes;
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
        "src" + fs + "main" + fs + "resources", 
        "python.pkg.simevolanalyzer",                       
        "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
        + "Struts2" + fs + "acdc" + fs + "dep",
        "org.apache.struts2"
      },
      
      {
    	  "src" + fs + "main" + fs + "resources", 
          "python.pkg.simevolanalyzer",                       
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "Struts2" + fs + "arc" + fs + "dep",
          "org.apache.struts2"
      },
      
      //set PkgPrefixes to empty string "" in C/C++ 
      {
    	  "src" + fs + "main" + fs + "resources", 
          "python.pkg.simevolanalyzer",                       
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "acdc" + fs + "dep",
          ""
      },
      
      {
    	  "src" + fs + "main" + fs + "resources", 
          "python.pkg.simevolanalyzer",                       
          "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs
          + "httpd" + fs + "arc" + fs + "dep",
          ""
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testBatchPackagerWrapperer()
  {    
    FeatureWrapper pkg = new BatchPackagerWrapper();    

    DepsDir.getInstance().setValue(depsDir);    
    PythonPackage.getInstance().setValue(pythonPackage);
    WorkingDir.getInstance().setValue(workingDir);    
    PkgPrefixes.getInstance().setValue(pkgPrefixes);

    try 
    { 
    	pkg.checkArguments(false);
    	pkg.execute(); 
    }
    catch(Exception e)
    {
      Assert.fail(e.getStackTrace().toString());
    }
    
  }
  //#endregion
}
