package edu.usc.softarch.arcade.frontend.features.acdc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.AcdcWithSmellDetection;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class AcdcWrapper
  implements FeatureWrapper
{
  //#region CONFIGURATION
  @Override
  public String getName() { return "acdc"; }

  @Override
  public String[] getArgumentIds()
  {
    return new String[] { "sourceDir", "acdcOutput", "binDir" };
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[3];
    parsedArgs[0] = args.get("sourceDir");
    parsedArgs[1] = args.get("acdcOutput") + fs + "acdc";
    parsedArgs[2] = args.get("binDir");
    AcdcWithSmellDetection.main(parsedArgs);
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(Map<String,String> args)
    throws IllegalArgumentException, IOException
  {
    // Check whether source directory exists
    File sourceDirectory = new File(args.get("sourceDir"));
    if(!sourceDirectory.exists())
    {
      String errorMessage = "Source directory not found: ";
      errorMessage += args.get("sourceDir");
      throw new IllegalArgumentException(errorMessage);
    }

    // Check whether output directory exists and, if not, create it
    String fs = File.separator;
    String outputDirPath = args.get("acdcOutput") + fs + "acdc";
    File outputDirectory = new File(outputDirPath);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    // Check binDir
    File[] sourceDirs = sourceDirectory.listFiles();
    for(File sDir : sourceDirs)
      // If no files inside the source directory are named binDir
      if(sDir.list((d, s) ->
        { return s.equals(args.get("binDir")); }).length != 1)
      {
        String errorMessage = "One or more source directories does not ";
        errorMessage +=  "contain the specified binary directory.";
        throw new IllegalArgumentException(errorMessage);
      }

    return true;
  }
  //#endregion
}
