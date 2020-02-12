package edu.usc.softarch.arcade.frontend.arghandlers;

import java.util.Map;

/**
 * ArgHandlers are singletons that specify the required operations for managing
 * the default behavior of an argument type that is shared across features.
 * ArgHandlers are supposed to be singletons.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ArgHandler
{
  //#region ATTRIBUTES
  private String value;
  private String name;
  private String description;
  private String instruction;
  //#endregion

  //#region CONSTRUCTORS
  protected void initialize(String name, String description, String instruction)
  {
    this.name = name;
    this.description = description;
    this.instruction = instruction;
  }
  //#endregion

  //#region CONFIGURATION
  /**
   * Returns the argument handler's name. This may not match the class or
   * package name, as it is used as an identifier within Arcade.
   *
   * @return Name of the argument handler.
   */
  public String getName() { return name; }

  /**
   * Returns the current value of the argument. If argument has not been set,
   * value is the empty String.
   *
   * @return Current value of the argument.
   */
  public String getValue() { return value; }

  /**
   * Sets a new value for the argument. New value must be valid.
   *
   * @return Previous value of the argument.
   * @throws IllegalArgumentException If new value provided is invalid.
   */
  public String setValue(String value)
    throws IllegalArgumentException
  {
    try { validate(value); }
    catch(Exception e)
    {
      throw new IllegalArgumentException(e);
    }

    String oldValue = this.value;
    this.value = value;
    return oldValue;
  }
  //#endregion

  //#region PRESENTATION
  /**
   * Gets a message representative of this argument handler's purpose. May be
   * used by UI components for displaying information about this argument.
   * Typically the name of the argument in human-readable format.
   *
   * @return The argument handler's description message.
   */
  public String getDescription() { return description; }

  /**
   * Gets an usage instruction for this argument. May be used by UI components
   * for explaining what inputs to provide.
   *
   * @return The argument's usage instruction.
   */
  public String getInstruction() { return instruction; }
  //#endregion

  //#region VALIDATION
  /**
   * Checks whether a given String is a valid value for this argument.
   *
   * @return True if String is valid.
   * @throws Exception Any exception appropriate to the validation algorithm.
   */
  public abstract boolean validate(String value)
    throws Exception;

  /**
   * Finds the appropriate argument string in an arguments map and checks
   * whether it is a valid value for this argument.
   *
   * @return True if value is valid.
   * @throws Exception Any exception appropriate to the validation algorithm.
   */
  public boolean validate(Map<String,String> args)
    throws Exception
  {
    return validate(args.get(getName()));
  }
  //#endregion
}
