package edu.usc.softarch.arcade.frontend.features.a2a;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.DistOpt;
import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;

@RunWith(Parameterized.class)
public class BatchSystemEvoWrapperTest
{
  //#region ATTRIBUTES
  String distOptVal;
  String sourceDirectory;
  //#endregion

  //#region CONSTRUCTOR
  public BatchSystemEvoWrapperTest(String distOptVal, String sourceDirectory)
  {
    super();
    this.distOptVal = distOptVal;
    this.sourceDirectory = sourceDirectory;
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
  public void testBatchSystemEvoWrapper()
  {
    FeatureWrapper a2a = new BatchSystemEvoWrapper();
    DistOpt.getInstance().setValue(distOptVal);
    SourceDir.getInstance().setValue(sourceDirectory);

    try
    {
      a2a.checkArguments(false);
      a2a.execute();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    // File output = new File(); //TODO fill in output file
    // assert output.exists();
  }
  //#endregion
}
