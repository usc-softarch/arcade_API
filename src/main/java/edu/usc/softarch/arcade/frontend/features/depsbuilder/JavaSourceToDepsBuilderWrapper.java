package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.facts.driver.JavaSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class JavaSourceToDepsBuilderWrapper
  implements FeatureWrapper
{
  @Override
  public String getName()
  {
    return arcade.strings.components.depsBuilder.java.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.sourceDir.id,
      arcade.strings.args.outputDir.id,
      arcade.strings.args.binDir.id
    };
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[2];
    parsedArgs[0] = args.get(arcade.strings.args.sourceDir.id);
    parsedArgs[0] += fs + args.get(arcade.strings.args.binDir.id);

    // String[] sourceDirPath = parsedArgs[0].split(fs);
    // String projectName = sourceDirPath[sourceDirPath.length-1];
    String projectName = "Struts2";

    parsedArgs[1] = args.get(arcade.strings.args.outputDir.id);
    parsedArgs[1] += fs + "commonRes" + fs + projectName + "_deps.rsf";
    JavaSourceToDepsBuilder.main(parsedArgs);
  }

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
    outputDirPath += fs + "commonRes";
    File outputDirectory = new File(outputDirPath);
    if(!outputDirectory.exists() && !outputDirectory.mkdirs())
      throw new IOException("Failed to create output directory.");

    // Check binDir
    String binDirPath = args.get(arcade.strings.args.sourceDir.id);
    binDirPath += fs + args.get(arcade.strings.args.binDir.id);
    File binDirectory = new File(binDirPath);
    if(!binDirectory.exists())
    {
      String errorMessage = "Classes directory not found: ";
      errorMessage += args.get(arcade.strings.args.binDir.id);
      throw new IllegalArgumentException(errorMessage);
    }

    return true;
  }
}
