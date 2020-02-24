package edu.usc.softarch.arcade.frontend.features;

import java.util.Arrays;
import java.lang.String;
import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;

/**
 * The FeatureWrapper interface specifies the necessary operations for
 * configuring and executing an ARCADE feature.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class FeatureWrapper
{
  //#region ATTRIBUTES
  private String id;
  private String name;
  private ArgHandler[] requiredArguments;
  private ArgHandler[] optionalArguments;
  //#endregion

  //#region CONSTRUCTORS
  /**
   * Should be called by the constructor of child classes to set the defining
   * attributes.
   *
   * @param id Identifier of the component.
   * @param name Full descriptor of the argument, typically its name.
   * @param requiredArguments ArgHandlers for the required arguments.
   * @param optionalArguments ArgHandlers for the optional arguments.
   */
  public void initialize(String id, String name,
    ArgHandler[] requiredArguments, ArgHandler[] optionalArguments)
  {
    this.id = id;
    this.name = name;
    this.requiredArguments = requiredArguments;
    this.optionalArguments = optionalArguments;
  }

  /**
   * Should be called by the constructor of child classes to set the defining
   * attributes. Sets optionalArguments to empty array.
   *
   * @param id Identifier of the component.
   * @param name Full descriptor of the argument, typically its name.
   * @param requiredArguments ArgHandlers for the required arguments.
   */
  public void initialize(String id, String name,
    ArgHandler[] requiredArguments)
  {
    initialize(id, name, requiredArguments, new ArgHandler[0]);
  }
  //#endregion

  //#region CONFIGURATION
  /**
   * @return Id of the component.
   */
  public String getId() { return id; }

  /**
   * @return Name of the component in human-readable format.
   */
  public String getName() { return name; }

  /**
   * @return Ids of the component's required arguments.
   */
  public String[] getRequiredArgumentIds()
  {
    return Arrays.stream(requiredArguments)
      .map(handler -> handler.getName()).toArray(String[]::new);
  }

  /**
   * @return Ids of the component's optional arguments.
   */
  public String[] getOptionalArgumentIds()
  {
    return Arrays.stream(optionalArguments)
      .map(handler -> handler.getName()).toArray(String[]::new);
  }

  /**
   * @return Argument handlers of this component's required arguments.
   */
  public ArgHandler[] getRequiredArgumentHandlers()
  {
    return requiredArguments.clone();
  }

  /**
   * @return Argument handlers of this component's optional arguments.
   */
  public ArgHandler[] getOptionalArgumentHandlers()
  {
    return optionalArguments.clone();
  }
  //#endregion

  //#region EXECUTION
  /**
   * Executes the component's main functionality. If it contains more
   * than one primary functionality, it is advised that the component be
   * refactored. Does not necessarily validate arguments automatically.
   *
   * @throws Exception Any exception appropriate to the wrapped feature.
   */
  public abstract void execute()
    throws Exception;
  //#endregion

  //#region VALIDATION
  /**
   * Checks whether the arguments available are appropriate for usage with
   * this component. Arguments must have been set in the appropriate
   * ArgHandlers. If any of the arguments are invalid, throws an exception
   * specifying which of them are invalid and how to fix them.
   *
   * @param checkOptional Whether to validate optional arguments as well.
   * @return True if arguments are valid.
   * @throws Exception List of invalid arguments and how to fix them.
   */
  public abstract boolean checkArguments(boolean checkOptional)
    throws Exception;
  //#endregion
}
