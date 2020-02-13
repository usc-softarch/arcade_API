package edu.usc.softarch.arcade.frontend.features.acdc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
import edu.usc.softarch.arcade.frontend.arghandlers.OutputDir;
import edu.usc.softarch.arcade.frontend.arghandlers.BinDir;
import edu.usc.softarch.arcade.frontend.arghandlers.SrcLanguage;

@RunWith(Parameterized.class)
public class AcdcDriverTest
{
  //#region ATTRIBUTES
  //TODO declare required Strings
  //#endregion

  //#region CONSTRUCTOR
  public AcdcDriverTest() //TODO fill in parameters
  {
    super();
    //TODO fill in assignments
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
        //TODO fill in parameters
      },
    });
  }
  //#endregion


  //#region TESTS
  @Test
  public void testAcdcDriver()
  {
    FeatureWrapper acdcDriver = new AcdcDriver();
    //TODO set parameter values

    try
    {
      acdcDriver.checkArguments();
      acdcDriver.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    //File output = new File(); //TODO fill in output file
    //assert output.exists();
  }
  //#endregion
}
