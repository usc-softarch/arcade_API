package edu.usc.softarch.arcade.frontend.exttooladapters.mallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter;

@RunWith(Parameterized.class)
public class InferencerTest
{
  //#region ATTRIBUTES
  String inputArgument;
  String inferencerFilename;
  //#endregion

  //#region CONSTRUCTOR
  public InferencerTest(String inputArgument, String inferencerFilename)
  {
    super();
    this.inputArgument = inputArgument;
    this.inferencerFilename = inferencerFilename;
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
        "--input src" + fs + "test" + fs + "resources" + fs + "subject_systems"
          + fs + "httpd" + fs + "arc" + fs + "base" + fs + "topicmodel.data",
        "--inferencer-filename src" + fs + "test" + fs + "resources" + fs
          + "subject_systems" + fs + "httpd" + fs + "arc" + fs + "base" + fs
          + "infer.mallet"
      }
    });
  }
  //#endregion

  //#region TESTS
  @Test
  public void testInferencer()
  {
    ExtToolAdapter inferencerAdapter = initialize();
    inferencerAdapter.addArguments(inputArgument);
    inferencerAdapter.addArguments(inferencerFilename);
    try { inferencerAdapter.execute(); }
    catch(InvocationTargetException e)
    {
      //TODO properly handle this
      e.printStackTrace();
    }
    //TODO assert whether the result of mallet execution is correct
  }
  //#endregion

  //#region INTERNAL
  private ExtToolAdapter initialize()
  {
    // Setting up auxiliary Strings
    String fs = File.separator;
    String resourcesDir = System.getProperty("user.dir") + fs + "src" + fs
      + "test" + fs + "resources";
    String malletHome = resourcesDir + fs + "tools" + fs + "mallet-2.0.7" + fs;

    //TODO set up an OS conditional to choose between Linux bash and Windows bat
    String commandPath = malletHome + "bin" + fs + "mallet.bat";
    Map<String,String> environment = new HashMap<String,String>();
    environment.put("MALLET_HOME", malletHome);

    // Setting up Inferencer
    ExtToolAdapter inferencerAdapter = new Inferencer();
    assert inferencerAdapter.setToolPath(commandPath);
    inferencerAdapter.setEnvironment(environment);
    return inferencerAdapter;
  }
  //#endregion
}
