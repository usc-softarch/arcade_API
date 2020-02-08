package edu.usc.softarch.arcade.frontend.features.arc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.clustering.BatchClusteringEngine;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

/**
 * See {@code edu.usc.softarch.arcade.clustering.BatchClusteringEngine}.
 */
public class ArcWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName() { return arcade.strings.components.arc.id; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.sourceDir.id,
      arcade.strings.args.outputDir.id,
      arcade.strings.args.binDir.id,
      arcade.strings.args.language.id
    };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[3];
    if(args.get(arcade.strings.args.language.id).equals("c"))
    {
      parsedArgs = new String[4];
      parsedArgs[3] = args.get(arcade.strings.args.language.id);
    }
    parsedArgs[0] = args.get(arcade.strings.args.sourceDir.id);
    parsedArgs[1] = args.get(arcade.strings.args.outputDir.id) + fs + "arc";
    parsedArgs[2] = args.get(arcade.strings.args.binDir.id);
    BatchClusteringEngine.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws IllegalArgumentException, IOException
  {
    // Check whether source directory exists
    File sourceDirectory = new File(args.get(arcade.strings.args.sourceDir.id));
    if(!sourceDirectory.exists())
    {
      String errorMessage = "Source directory not found: ";
      errorMessage += args.get(arcade.strings.args.sourceDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether output directory exists and, if not, create it
    String fs = File.separator;
    String outputDirPath = args.get(arcade.strings.args.outputDir.id);
    outputDirPath += fs + "arc";
    File outputDirectory = new File(outputDirPath);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    // Check language and binDir
    if(args.get(arcade.strings.args.language.id).equals("java"))
    {
      File[] sourceDirs = sourceDirectory.listFiles();
      for(File sDir : sourceDirs)
        // If no files inside the source directory are named binDir
        if(sDir.list((d, s) ->
          { return s.equals(args.get(arcade.strings.args.binDir.id)); })
            .length != 1)
        {
          String errorMessage = "One or more source directories does not ";
          errorMessage +=  "contain the specified binary directory.";
          throw new IllegalArgumentException(errorMessage);
        }
    }

    return true;
  }
  //#endregion
}
