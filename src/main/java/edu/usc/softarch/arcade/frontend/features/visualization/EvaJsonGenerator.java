package edu.usc.softarch.arcade.frontend.features.visualization;

import java.util.List;
import java.util.LinkedList;

import edu.usc.softarch.rsfutil.Converter;
import edu.usc.softarch.rsfutil.ConverterEvaJsonHack;
import edu.usc.softarch.rsfutil.GenericFilter;

import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
import edu.usc.softarch.arcade.frontend.arghandlers.ClusterFile;
import edu.usc.softarch.arcade.frontend.arghandlers.EvaJsonFile;

//TODO This whole class needs to be refactored
public class EvaJsonGenerator
  extends FeatureWrapper
{
  //#region CONSTRUCTORS
  public EvaJsonGenerator()
  {
    String id = "evaJsonGenerator";
    String name = "EVA Json File Generator";
    ArgHandler[] requiredArguments =
    {
      ClusterFile.getInstance(),
      EvaJsonFile.getInstance()
    };
    initialize(id, name, requiredArguments);
  }
  //#endregion

  //#region EXECUTION
  @Override
  public void execute()
    throws Exception
  {
    List<GenericFilter> filters = new LinkedList<GenericFilter>();
    Converter jsonGen = new ConverterEvaJsonHack(filters);
    jsonGen.convertToFile(ClusterFile.getInstance().getValue(),
      EvaJsonFile.getInstance().getValue());
  }
  //#endregion

  //#region VALIDATION
  @Override
  public boolean checkArguments(boolean checkOptional)
    throws Exception
  {
    boolean sourceDirPathValid = ClusterFile.getInstance().validateAsInput();
    boolean outputDirValid = EvaJsonFile.getInstance().validateAsOutput();

    return (sourceDirPathValid && outputDirValid);
  }
  //#endregion
}
