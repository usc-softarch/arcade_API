package edu.usc.softarch.arcade.frontend.features.depsbuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import edu.usc.softarch.arcade.facts.driver.CSourceToDepsBuilder;
import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;

public class CSourceToDepsBuilderWrapper
  implements FeatureWrapper
{
  @Override
  public String getName()
  {
    return arcade.strings.components.depsBuilder.c.id;
  }

  @Override
  public String[] getArgumentIds()
  {
    return new String[]
    {
      arcade.strings.args.sourceDir.id,
      arcade.strings.args.depsRsfFile.id
    };
  }

  @Override
  public void execute(Map<String,String> args)
    throws Exception, IOException, IllegalArgumentException
  {
    String fs = File.separator;
    String[] parsedArgs = new String[2];
    parsedArgs[0] = args.get(arcade.strings.args.sourceDir.id);
    parsedArgs[1] = args.get(arcade.strings.args.depsRsfFile.id);

    CSourceToDepsBuilder.main(parsedArgs);
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

    return true;
  }
}
