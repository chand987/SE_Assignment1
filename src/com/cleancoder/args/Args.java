
/**
 * @author Runa Chand
 */

package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.UNEXPECTED_ARGUMENT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * This is the root class.Rest of the methods 
 are linked with this class.
 * @see Args
 * @see ArgsData
 * @see ArgsException
 */
public class Args {
  private ArgsData data = new ArgsData();
  
  /**
  * Processes {@code args} string array defined in {@code schema} string.
  * @param schema Definition of Schema(elementId + elementTail) separated by commas.
  * @param args  String array which may contain command line input. 
  * <li>Example: {@code "-p 56 -ch zxcv -d 5.6"}</li>
  * @throws ArgsException {@link com.cleancoder.args.ArgsException}
  */
  public Args(String schema, String[] args) throws ArgsException {
    data.marshalers = new HashMap<Character, ArgumentMarshaler>();
    data.argsFound = new HashSet<Character>();   
    data.parseSchema(schema);
    parseArgumentStrings(Arrays.asList(args));
  }

  /**
  * Parses out {@code elementId}(s) from {@code args} string.
  * @param argsList List of substrings of {@code args} string.
  * @throws ArgsException {@link com.cleancoder.args.ArgsException}
  */
  private void parseArgumentStrings(List<String> argsList) throws ArgsException {
    for (data.currentArgument = argsList.listIterator(); data.currentArgument.hasNext();) {
      String argString = data.currentArgument.next();
      if (argString.startsWith("-")) {
        parseArgumentCharacters(argString.substring(1));
      } else {
        data.currentArgument.previous();
        break;
      }
    }
  }

  /**
  * Passes {@code elementId}(s)which is being extracted from {@code args}
  to {@link com.cleancoder.args.Args#parseArgumentCharacter parseArgumentCharacter} method.
  * @param argChars List of {@code elementId}s extracted from {@code args}.
  * @throws ArgsException {@link com.cleancoder.args.ArgsException}
  */
  private void parseArgumentCharacters(String argChars) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++) {
      parseArgumentCharacter(argChars.charAt(i));
    }
  }

  /** 
  * Validates {@code elementId} character given in command line argument
  * to check if a matching schema exists.
  * @param argChar schema given in command line input
  * @throws ArgsException {@code UNEXPECTED_ARGUMENT} if flag the flags doesn't match with schema.
  * @see com.cleancoder.args.ArgsData#argsFound method.
  */
  private void parseArgumentCharacter(char argChar) throws ArgsException {
    ArgumentMarshaler m = setArgumentCharacter(argChar);
    if (m == null) {
      throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
    } else {
      data.argsFound.add(argChar);
    }
  }

  /** 
  * Checks If {@code True} then {@code marshaler} corresponding to that {@code elementId} is executed.
  * @param argChar element from{@code elementId}(s) extracted from {@code args} string.
  * @throws ArgsException {@link com.cleancoder.args.ArgsException}
  */
  private ArgumentMarshaler setArgumentCharacter(char argChar) throws ArgsException {
    ArgumentMarshaler m = data.marshalers.get(argChar);
    if (m == null) {
      throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
    } else {
      try {
        m.set(data.currentArgument);
      } catch (ArgsException e) {
        e.setErrorArgumentId(argChar);
        throw e;
      }
    }
    return m;
  }

  public boolean has(char arg) {
    return data.argsFound.contains(arg);
  }

  public int nextArgument() {
    return data.currentArgument.nextIndex();
  }

  public boolean getBoolean(char arg) {
    return BooleanArgumentMarshaler.getValue(data.marshalers.get(arg));
  }

  public String getString(char arg) {
    return StringArgumentMarshaler.getValue(data.marshalers.get(arg));
  }

  public int getInt(char arg) {
    return IntegerArgumentMarshaler.getValue(data.marshalers.get(arg));
  }

  public double getDouble(char arg) {
    return DoubleArgumentMarshaler.getValue(data.marshalers.get(arg));
  }

  public String[] getStringArray(char arg) {
    return StringArrayArgumentMarshaler.getValue(data.marshalers.get(arg));
  }

  public Map<String, String> getMap(char arg) {
    return MapArgumentMarshaler.getValue(data.marshalers.get(arg));
  }
}