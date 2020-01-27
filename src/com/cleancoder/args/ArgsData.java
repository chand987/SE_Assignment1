package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static com.cleancoder.args.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * The purpose of this class is to process the {@code schema} argument 
 in {@link com.cleancoder.args.Args#Args Args} constructor and to create mappings between
 schemas and marshalers.
 */
public class ArgsData {
  public Map<Character, ArgumentMarshaler> marshalers;
  public Set<Character> argsFound;
  public ListIterator<String> currentArgument;
   
  /**
  * Separate the {@code schema} string by commas. 
  * @param schema It is defined in the main file. 
  * @throws ArgsException ArgsException {@link com.cleancoder.args.ArgsException}
  */
  public void parseSchema(String schema) throws ArgsException {
    for (String element : schema.split(",")) {
      if (element.length() > 0) {
        parseSchemaElement(element.trim());
      }
    }
  }
  
  /**
   * Build map between {@code elementTail} and relevant marshalers by comparing
   {@code elementTail} with tail strings which is predefined.
   * @param element schema string as defined in main program.
   * @throws ArgsException Throws exception {@code INVALID_ARGUMENT_FORMAT} 
   if matching tail string is not found.
   */
  private void parseSchemaElement(String element) throws ArgsException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);
    validateSchemaElementId(elementId);
    if (elementTail.length() == 0) {
      marshalers.put(elementId, new BooleanArgumentMarshaler());
    } else if (elementTail.equals("*")) {
      marshalers.put(elementId, new StringArgumentMarshaler());
    } else if (elementTail.equals("#")) {
      marshalers.put(elementId, new IntegerArgumentMarshaler());
    } else if (elementTail.equals("##")) {
      marshalers.put(elementId, new DoubleArgumentMarshaler());
    } else if (elementTail.equals("[*]")) {
      marshalers.put(elementId, new StringArrayArgumentMarshaler());
    } else if (elementTail.equals("&")) {
      marshalers.put(elementId, new MapArgumentMarshaler());
    } else {
      throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }
  }

  /**
   * Checks if the 1st character of each substring in {@code schema} is a letter or not.
   * @param elementId first character of the schema substring. 
   * @throws ArgsException {@code INVALID_ARGUMENT_NAME} if the first character is not a letter.
   */
  private void validateSchemaElementId(char elementId) throws ArgsException {
    if (!Character.isLetter(elementId)) {
      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }
  }
}