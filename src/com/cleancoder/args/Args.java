package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.UNEXPECTED_ARGUMENT;

import java.util.*;

public class Args {
  private ArgsData data = new ArgsData();

  public Args(String schema, String[] args) throws ArgsException {
    data.marshalers = new HashMap<Character, ArgumentMarshaler>();
    data.argsFound = new HashSet<Character>();
   
    data.parseSchema(schema);
    parseArgumentStrings(Arrays.asList(args));
  }

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

  private void parseArgumentCharacters(String argChars) throws ArgsException {
    for (int i = 0; i < argChars.length(); i++) {
      parseArgumentCharacter(argChars.charAt(i));
    }
  }

  private void parseArgumentCharacter(char argChar) throws ArgsException {
    ArgumentMarshaler m = setArgumentCharacter(argChar);
    if (m == null) {
      throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
    } else {
      data.argsFound.add(argChar);
    }
  }

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