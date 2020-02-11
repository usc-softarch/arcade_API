package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * ArgHandlers are singletons that specify the required operations for managing
 * the default behavior of an argument type that is shared across features.
 * ArgHandlers are supposed to be singletons.
 *
 * @author Marcelo Schmitt Laser
 */
public interface ArgHandler
{
  //#region CONFIGURATION
  /**
   * Returns the argument handler's name. This may not match the class or
   * package name, as it is used as an identifier within Arcade.
   *
   * @return Name of the argument handler.
   */
  public String getName();

  /**
   * Returns the current value of the argument. If argument has not been set,
   * value is the empty String.
   *
   * @return Current value of the argument.
   */
  public String getValue();

  /**
   * Sets a new value for the argument. New value must be valid.
   *
   * @return Previous value of the argument.
   * @throws IllegalArgumentException If new value provided is invalid.
   */
  public String setValue(String value)
    throws IllegalArgumentException;
  //#endregion

  //#region PRESENTATION
  /**
   * Gets a message representative of this argument handler's purpose. May be
   * used by UI components for displaying information about this argument.
   * Typically the name of the argument in human-readable format.
   *
   * @return The argument handler's description message.
   */
  public String getDescription();

  /**
   * Gets an usage instruction for this argument. May be used by UI components
   * for explaining what inputs to provide.
   *
   * @return The argument's usage instruction.
   */
  public String getInstruction();
  //#endregion

  //#region VALIDATION
  /**
   * Checks whether a given String is a valid value for this argument.
   *
   * @return True if String is valid.
   * @throws Exception Any exception appropriate to the validation algorithm.
   */
  public boolean validate(String value)
    throws Exception;
  //#endregion
}
