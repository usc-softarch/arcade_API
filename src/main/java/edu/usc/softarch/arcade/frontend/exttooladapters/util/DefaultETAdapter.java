package edu.usc.softarch.arcade.frontend.exttooladapters.util;

import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Map;
import edu.usc.softarch.arcade.frontend.exttooladapters.util.ExtToolAdapter;

//TODO documentation
public abstract class DefaultETAdapter
  extends ExtToolAdapter
{
  //#region ATTRIBUTES
  private String ignore;
  private String require;
  private boolean prepend;
  private boolean isJar;
  //#endregion

  //#region CONSTRUCTOR
  //TODO document
  public DefaultETAdapter(String ignore, String require)
  {
    initialize(ignore, require, true, false);
  }

  //TODO document
  public DefaultETAdapter(String path, String ignore, String require)
    throws FileNotFoundException
  {
    super(path);
    initialize(ignore, require, true, false);
  }

  //TODO document
  public DefaultETAdapter(
    String ignore, String require, boolean prepend, boolean isJar)
  {
    super();
    initialize(ignore, require, prepend, isJar);
  }

  //TODO document
  public DefaultETAdapter(
    String path, String ignore, String require, boolean prepend, boolean isJar)
    throws FileNotFoundException
  {
    super(path);
    initialize(ignore, require, prepend, isJar);
  }

  //TODO document
  private void initialize(
    String ignore, String require, boolean prepend, boolean isJar)
  {
    this.ignore = ignore;
    this.require = require;
    this.prepend = prepend;
    this.isJar = isJar;
  }
  //#endregion

  //#region ABSTRACT_METHODS
  //TODO document
  protected abstract void exceptionHandling(Process p)
    throws InvocationTargetException;
  //#endregion

  //#region INTERFACE
  //TODO document this method properly
  //TODO add order validation
  @Override
  protected String[] validateArguments(String[] arguments)
    throws IllegalArgumentException
  {
    List<String> newArguments = new ArrayList<String>();
    int numFlags = require.split(Pattern.quote("|")).length;
    List<String> flags = new ArrayList<String>();

    if(!this.ignore.isEmpty())
    {
      String[] constantArguments = ignore.split(Pattern.quote("|") + "| ");
      for(String s : constantArguments) { newArguments.add(s); }
    }

    for(String s : arguments)
    {
      if(!s.matches(ignore))
      {
        String[] tokens = s.split(" ");
        if(!tokens[0].matches(require))
          throw new IllegalArgumentException("Unknown argument");
          //TODO Throw exception due to unknown argument
        if(flags.contains(tokens[0]))
          throw new IllegalArgumentException("Repeat argument");
          //TODO Throw exception due to repeat argument
        //TODO create output directory if necessary
        flags.add(tokens[0]);
        if(prepend) newArguments.add(tokens[0]);
        newArguments.add(tokens[1]);
      }
    }

    if(flags.size() != numFlags);
      //TODO Throw exception due to missing arguments

    return newArguments.toArray(new String[0]);
  }

  @Override
  protected Object[] executeInternal(String path,
    String[] arguments, Map<String,String> environment)
    throws InvocationTargetException
  {
    List<String> command = new ArrayList<String>();
    //TODO comment this
    if(this.isJar)
    {
      command.add("java");
      command.add("-jar");
    }
    command.add(path);
    for(String s : arguments)
      command.add(s);

    try
    {
      ProcessBuilder pb = new ProcessBuilder(command);
      System.out.println(pb.command());
      pb.environment().putAll(environment);
      pb.inheritIO();
      Process p = pb.start();
      p.waitFor();
      exceptionHandling(p);
    }
    catch(SecurityException e)
    {
      e.printStackTrace();
      //TODO throw exception from lack of permission
    }
    catch(IOException e)
    {
      e.printStackTrace();
      //TODO throw exception due to I/O error
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
      //TODO throw exception due to some crazy threading error
    }
    catch(NullPointerException | IllegalArgumentException e)
    {
      e.printStackTrace();
      //TODO throw exception due to herpaderp
    }

    return null;
  }
  //#endregion
}
