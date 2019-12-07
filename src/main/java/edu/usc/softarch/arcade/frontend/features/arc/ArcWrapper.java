package edu.usc.softarch.arcade.frontend.features.arc;

import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import edu.usc.softarch.arcade.clustering.BatchClusteringEngine;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;

public class ArcWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final int ARGS_SIZE = 3;
  //#endregion

  //#region INTERFACE
  @Override
  public String getName() { return "arc"; }

  @Override
  public Object[] execute(Object[] args)
    throws Exception, IOException, IllegalArgumentException
  {
    BatchClusteringEngine.main(
      Arrays.copyOf(args, args.length, String[].class));
    return null;
  }

  @Override
  public boolean checkArguments(String[] args)
    throws IllegalArgumentException, IOException
  {
    checkArgsSize(args);

    File sourceDirectory = new File(args[0]);
    if(!sourceDirectory.exists())
      throw new IllegalArgumentException("Source directory not found.");

    File outputDirectory = new File(args[1]);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    return true;
  }

  @Override
  public boolean checkArguments(Object[] args)
    throws IllegalArgumentException, IOException
  {
    checkArgsSize(args);
    // checkArgsSize is run twice for checkArguments(Object[] args): once here,
    // once when calling checkArguments(String[] args). This is done to ensure
    // the two methods are decoupled, but also that this overload does not
    // hang due to excess number of arguments in the following loop.
    for(Object o : args)
    {
      if(!o.getClass().equals(String.class))
        throw new IllegalArgumentException
          ("One or more arguments not a String.");
    }

    return checkArguments((String[])args);
  }
  //#endregion

  //#region INTERNAL
  private void checkArgsSize(Object[] args)
    throws IllegalArgumentException
  {
    if(args.length > ARGS_SIZE)
      throw new IllegalArgumentException("Excess number of arguments.");
    if(args.length < ARGS_SIZE)
      throw new IllegalArgumentException("Missing arguments.");
  }
  //#endregion
}
