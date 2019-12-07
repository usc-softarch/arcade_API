package edu.usc.softarch.arcade.frontend.features.pipeextractor;

import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import edu.usc.softarch.arcade.util.ldasupport.PipeExtractor;
import edu.usc.softarch.arcade.frontend.features.wrappers.FeatureWrapper;

public class PipeExtractorWrapper
  implements FeatureWrapper
{
  //#region ATTRIBUTES
  private static final int ARGS_SIZE = 2;
  //#endregion

  //#region INTERFACE
  @Override
  public String getName() { return "pipeextractor"; }

  @Override
  public Object[] execute(Object[] args)
    throws FileNotFoundException, IOException, IllegalArgumentException
  {
    PipeExtractor.main(
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

    return checkArguments(Arrays.copyOf(args, args.length, String[].class));
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
