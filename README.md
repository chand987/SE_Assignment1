
<div align=center>
 <h1>JavaArgs</h1>
  <h1>Code Cleaning </h1>
  <h1>Software Engineering (Assignment-1)</h1>
  <div align=right>
  <b>Submitted By:</b>
  <b>Runa Chand</b>
  <div align=right>
  <b>Roll no:</b>
  <b>2019900069</b>
 
<div align=left>  

## Disclaimer :

This segment of code is being forked from the Args program described in [Uncle Bob](http://butunclebob.com/ArticleS.UncleBob.CleanCodeArgs). JavaArgs is a java version of Args that is described by **Uncle Bob** in his book called "*Clean Code*".


This is an assignment given in Software Engineering course(2020) at [International Institute of Information Technology](https://stackedit.io/www.iiit.ac.in) to have a good practice in code cleaning.

## Table of Contents

[Objective](https://github.com/chand987/SE_Assignment1/blob/master/README.md#objective-)

[Installation](https://github.com/chand987/SE_Assignment1#installation-)

[Before You Run The Code](https://github.com/chand987/SE_Assignment1#before-you-run-the-code-)

[For Tests](https://github.com/chand987/SE_Assignment1#for-tests-)

[Usage](https://github.com/chand987/SE_Assignment1#usage-)
* [Instructions](https://github.com/chand987/SE_Assignment1#instructions)
* [Schema](https://github.com/chand987/SE_Assignment1#schema-)

[Understanding the Code](https://github.com/chand987/SE_Assignment1#understanding-the-code-)
* [JavaDocs](https://github.com/chand987/SE_Assignment1#javadocs-)

[Clean Code](https://github.com/chand987/SE_Assignment1#clean-code-)
* [Code Coverage](https://github.com/chand987/SE_Assignment1#code-coverage-)
  * [Removed uncovered methods and lines](https://github.com/chand987/SE_Assignment1#removed-uncovered-methods-and-lines)
  * [Test case added(1)](https://github.com/chand987/SE_Assignment1#test-case-added)
  * [Test case added(2)]()
* [Code Smells](https://github.com/chand987/SE_Assignment1#code-smells-)
	* [Long Method](https://github.com/chand987/SE_Assignment1/blob/master/README.md#long-method)
	* [God Class](https://github.com/chand987/SE_Assignment1/blob/master/README.md#god-class)
* [Linting Improvements](https://github.com/chand987/SE_Assignment1/blob/master/README.md#linting-improvements-)
* [Finding Bugs]()

## Objective :

The objective of this assignment is to have a good practice in code cleaning for JavaArgs which includes the following :

1. Imporving Code-coverage
   * Add tests for uncovered code 
   * Remove duplicate tests 
   * Add Test Annotation [Decorators]
   * Refactor if/else/try/catch conditions to improve coverage
2. Refactor Control Flow
3. Refactor Classes and Methods
4. End to End Test
5. Remove Code Smells 
6. Removing Lints 

The guidelines and  details are given below to perform the above actions.

### Installation :
Use   [eclipse IDE]([https://www.eclipse.org/ide/](https://www.eclipse.org/ide/)) to run JavaArgs

Run
```
  * sudo add-apt-repository ppa:openjdk-r/ppa
  * sudo apt-get update -q 
  * sudo apt install -y openjdk-11-jdk   
 ```

### Before You Run The Code :

Before you Run the code, you need to check for the environment setup for Java:
```
  * install ant by running 'sudo apt-get install ant'
  * then go to the folder where you have cloned this repo
  * run 'ant compile'
  * run 'ant jar'
  * run 'java -cp build/jar/args.jar com.cleancoder.args.ArgsMain'
```

### For Tests :

Run

```
 * Run the command given below from the root folder of this repo
 * 'java -cp "lib/junit-4.13.jar:lib/hamcrest-core-1.3.jar:build/jar/args.jar" ./test/com/cleancoder/args/ArgsTest.java testCreateWithNoSchemaOrArguments'
```

## Usage :

Once you have set-up the basic requirements and your environment is ready, you may use the following code to implement the main class ```ArgsMain.java```

```Java
package com.cleancoder.args;

import java.util.Map;

public class ArgsMain {

  public static void main(String[] args) {
    try {
      Args arg = new Args("l,p#,d*,b##, c[*], m&", args);
      boolean logging = arg.getBoolean('l');
      int port = arg.getInt('p');
      double dob = arg.getDouble('b');
      String[] cs = arg.getStringArray('c');
      Map<String, String> ma = arg.getMap('m');
     
      String directory = arg.getString('d');
      executeApplication(logging, port, directory, dob, cs, ma);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static void executeApplication(boolean logging, int port, String directory, double dob, String[] cs, Map<String, String> ma) {
    System.out.printf("logging is %s, port:%d, directory:%s, double:%f, stringArray:%s, map:%s",logging, port, directory, dob, cs[0], ma);
  }
}
```

### Instructions:

Add ```ArgsMain.java``` file in the follwing path : ```root/src/com/cleancoder/args ``` in your code and operate by using the schema given below.

### Schema :
```
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.
```

Example schema: (f,s*,n#,a##,p[*])

Corresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3     


## Understanding the Code :

### JavaDocs :

To understand the code better follow the [JavaDoc](https://raw.githack.com/chand987/SE_Assignment1/master/doc/index.html) given for the code.

*JavaDocs for  tests are not available. So to reduce the on-screen clutter of code, I have tried to make classes/methods/ arg names to be more descriptive in-order to save the clutter.*

For more discussion on JavaDocs for test check this [Stackoverflow](https://stackoverflow.com/a/2968153/3801905)  Link.




## Clean Code :

I am going to discuss about the code cleaning that has been done for the JavaArgs Code in this section .

There are some major practices I used to clean the code, Those are:
```
1. Removing Code Smells 
2. Imporving Code-coverage
  * Add tests for uncovered code 
  * Remove duplicate tests 
  * Add Test Annotation [Decorators]
  * Refactoring if/else/try/catch conditions to improve coverage
4. Refactoring Control Flow
5. Refactoring Classes and Methods
6. End to End Testing
7. Removing Code Lint 

```
For the above changes some tools were used which are mentioned below:

* Code Coverage: I used [Junit](https://junit.org/junit5/) 
* Code Smell : I used  [Jdeodrant](http://jdeodorant.com/)  integration to remove code smells and lints.
 *  Linting : I used [CheckStyle](https://marketplace.eclipse.org/content/checkstyle-plug) to remove lint from code 

Now the methods of code cleaning are discussed below in details.

### Code Coverage :

* Code coverage has been imporved from **88.5 %**  to **91%** overall.
* Code coverage is at a whopping **97%** if tests are not considered.
* As per the discussion online, tests are generally not covered during the coverage. For more information check this [Stackoverflow](https://stackoverflow.com/a/24958299/3801905) link.

#### Improvements of Code Coverage:

The refactored segment of code that helped imporve Code Coverage are given below:

#### Removed uncovered methods and lines:

 Unused lines for methods were removed from the code to improve coverage as they were affecting significantly.

From ```ArgsException.java``` remove 
```
public  ArgsException() {}

public  ArgsException(String message) {super(message);}
```
Repeat the same again.

```
public void setErrorCode(ErrorCode  errorCode) {  this.errorCode = errorCode;}
```

#### Test case added(1):
In  ```ArgsExceptionTest.java```  Test for OK condition is added.

```testOkMessage()```  was not available in the code and  **OK** enum was not covered. 

To fix it, below piece of code was added :

```
public void testOkMessage() throws Exception {

ArgsException e = new ArgsException(OK, 'x', null);

assertEquals("TILT: Should not get here.", e.errorMessage());

}

```
#### Test case added(2):
In ```ArgsTest.java``` test case added  for concatenated type strings as arguments. 

```
  @Test 
  public void testContinuousFlags() throws Exception {
    Args argsData = new Args("x#,y##", new String[]{"-xy", "20", "4.5"});
    assertTrue(argsData.has('x'));
    assertTrue(argsData.has('y'));
    assertEquals(20, argsData.getInt('x'));
    assertEquals(4.5, argsData.getDouble('y'),.001);
  
  }
  
```
### Code Smells :
##### Definition of code smell:
 Code smells are certain structures in the code that indicate violation of fundamental design principles and negatively impact design quality. Code smells are usually not; they are not technically incorrect and do not prevent the program from functioning. Instead, they indicate weaknesses in design that may slow down development or increase the risk of bugs or failures in the future.

The vanilla code from base repository had some major code smells in  the follwing files 

```Args.java```
```ArgsData.java```

So in order to remove code smells I made the following changes.

Those were in 

#### Long Method
* Extract Method for variable criteria **'m'**

The changes that are stated below are in-coherence with the following manual which clearly states the decomposition methodology. For step by step details about code smell(Long method) improvement, refer  [Jdeodrant](https://users.encs.concordia.ca/~nikolaos/jdeodorant/index.php?option=com_content&view=article&id=45)

Name | Refactoring Type | Variable Criteria
--- | --- | ---
Long method | Extract method | **m**

---

```
if (m == null) {

throw new  ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
```
#### God Class
* for ```schema``` Extract Class

For step by step details about code smell (God class) improvement, refer  [Jdeodrant](https://users.encs.concordia.ca/~nikolaos/jdeodorant/index.php?option=com_content&view=article&id=45)

 File ```ArgsData.java``` was created in order to decompose the following class:

Name | Refactoring Type | Variable Criteria
--- | --- | ---
God Class | Extract Class | Schema






 Following Code from ```Args.java``` has been decompsed into ```ArgsData.java```

```
private void parseSchemaElement(String element) throws ArgsException { 

    char elementId = element.charAt(0); 

    String elementTail = element.substring(1); 

    validateSchemaElementId(elementId); 

    if (elementTail.length() == 0) 

      marshalers.put(elementId, new BooleanArgumentMarshaler()); 

    else if (elementTail.equals("*")) 

      marshalers.put(elementId, new StringArgumentMarshaler()); 

    else if (elementTail.equals("#")) 

      marshalers.put(elementId, new IntegerArgumentMarshaler()); 

    else if (elementTail.equals("##")) 

      marshalers.put(elementId, new DoubleArgumentMarshaler()); 

    else if (elementTail.equals("[*]")) 

      marshalers.put(elementId, new StringArrayArgumentMarshaler()); 

    else if (elementTail.equals("&")) 

      marshalers.put(elementId, new MapArgumentMarshaler()); 

    else 

      throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail); 

  } 

  private void validateSchemaElementId(char elementId) throws ArgsException { 

    if (!Character.isLetter(elementId)) 

      throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null); 

  } 
  
  public boolean getBoolean(char arg) { 

    return BooleanArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 

  public String getString(char arg) { 

    return StringArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 

  public int getInt(char arg) { 

    return IntegerArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 
  
  public double getDouble(char arg) { 

    return DoubleArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 

  public String[] getStringArray(char arg) { 

    return StringArrayArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 

  public Map<String, String> getMap(char arg) { 

    return MapArgumentMarshaler.getValue(marshalers.get(arg)); 

  } 

} 
```
### Linting Improvements :
##### Definition of Linting:
Linting is the process of running a program that analyzes your code for programmatic and stylistic errors. A linting tool, or a linter, marks or flags any potential errors in your code such as syntax errors or incorrectly spelled variable names.

To improve linting, solutions are given below.

From ```ArgsData.java```  remove 

```
public ArgsData() {}

```
* Removed wildcards from all import statements in the project. [also from java utils]

There are various ways to add *imports* in java and one of the two ways is adding imports with ```wildcards (*)```, I chose to remove all the wildcards and manually add imports taking a reference from the following discussion [Stackoverflow](https://stackoverflow.com/questions/147454/why-using-a-wild-card-with-a-java-import-statement-bad/147461#147461) .

* Followed some code conventions where I added ```static``` imports before all the util imports  and arranged alphabetically.


* In file ```Argsexception.java ```  ```Return``` statement shifted from outside of switch case to default case inside switch case.

```
default:

return; // return statement shifted here

}
```
## Finding Bug

JavaArgs program has a bug in the schema which is described below :

The arguments can read only a single command line character that is passed and ignore the characters passed after.

For example

```
Args args = new Args("x,y", new String[]{"-x", "abcd", "-y", "zxcv"});

```
In the above code the arguments taken and tested are primarily abcd and not zxcv if passed as serial arguments.

<b>Fix

This can be fixed by changing the schema definition handling and parsing in the code.




## License
[MIT](https://choosealicense.com/licenses/mit/)
