package edu.usc.softarch.arcade.frontend.arghandlers;

/**
 * ArgHandlers are singletons that specify the required operations for managing
 * the default behavior of an argument type that is shared across features.
 *
 * @author Marcelo Schmitt Laser
 */
public abstract class ArgHandler
{
  //#region ATTRIBUTES
  private String value;
  private String id;
  private String description;
  private String instruction;
  //#endregion

  //#region CONSTRUCTORS
  /**
   * Should be called by the constructor of child classes to set the defining
   * attributes.
   *
   * @param id Identifier of the argument.
   * @param description Full descriptor of the argument, typically its name.
   * @param instruction Usage instructions of the argument.
   */
  protected void initialize(String id, String description, String instruction)
  {
    this.id = id;
    this.description = description;
    this.instruction = instruction;
  }
  //#endregion

  //#region CONFIGURATION
  /**
   * @return Id of the argument handler.
   */
  public String getId() { return id; }

  /**
   * @return Current value of the argument. If argument has not been set,
   * value is the empty String.
   */
  public String getValue() { return value; }

  /**
   * Sets a new value for the argument. New value is not validated.
   *
   * @param value New value of the argument.
   * @return Previous value of the argument.
   */
  public String setValue(String value)
  {	
    String oldValue = this.value;
    this.value = value;
    return oldValue;
  }
  //#endregion

  //#region PRESENTATION
  /**
   * Gets a description of this argument. Typically the name of the argument
   * in human-readable format.
   *
   * @return The argument handler's descriptor.
   */
  public String getDescription() { return description; }

  /**
   * @return The argument's usage instruction.
   */
  public String getInstruction() { return instruction; }
  //#endregion

  //#region VALIDATION
  /**
   * Checks whether a given String is a valid input value for this argument.
   * If the value provided is invalid, an exception will be thrown with
   * instructions on how to fix the argument, if possible.
   *
   * @param value String value to validate.
   * @return True if String is valid.
   * @throws Exception Reason for invalidity and fix suggestions.
   */
  public abstract boolean validateAsInput(String value)
    throws Exception;

  /**
   * Checks whether a given String is a valid output value for this argument.
   * If the value provided is invalid, an exception will be thrown with
   * instructions on how to fix the argument, if possible.
   *
   * @param value String value to validate.
   * @return True if String is valid.
   * @throws Exception Reason for invalidity and fix suggestions.
   */
  public abstract boolean validateAsOutput(String value)
    throws Exception;

  /**
   * Checks whether the stored String is a valid input value for this argument.
   * If the stored value is invalid, an exception will be thrown with
   * instructions on how to fix the argument, if possible.
   *
   * @return True if String is valid.
   * @throws Exception Reason for invalidity and fix suggestions.
   */
  public boolean validateAsInput()
    throws Exception
  {
    return validateAsInput(getValue());
  }

  /**
   * Checks whether the stored String is a valid output value for this argument.
   * If the stored value is invalid, an exception will be thrown with
   * instructions on how to fix the argument, if possible.
   *
   * @return True if String is valid.
   * @throws Exception Reason for invalidity and fix suggestions.
   */
  public boolean validateAsOutput()
    throws Exception
  {
    return validateAsOutput(getValue());
  }
  //#endregion
}
