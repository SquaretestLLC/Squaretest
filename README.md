# Squaretest Java Unit Test Generator for IntelliJ IDEA

The Squaretest plugin for IntelliJ IDEA allows you to automatically generate unit tests for your Java source classes.
The generated test classes contain code to construct the instance of the source class (if needed) and initialize the
dependencies to either mocks or reasonable default values. The test classes include test methods that invoke their corresponding source methods
and compare the returned values to expected values. The tests also include Mockito stubs; i.e. when(), doAnswer() statements and verify() statements.

## Create a Test Class for a Java Class
Select **Squaretest | Generate Test - Ask to Confirm Mocks** (Alt+Insert+Generate Test - Ask to Confirm Mocks) to create a test class for your Java source class.

![Generate Test Gif](https://squaretest.com/images/gifs/V1_5/CreateTestClass.gif)

## Create Test Methods
Select **Squaretest | Generate Test Methods** (Alt+Insert+Generate Test Methods) to see a list of test methods to add to your test class.
The list includes tests for alternate flows for each source method; e.g. if a source method calls foo.bar(), and bar() can throw an IOException, Squaretest will suggest a method called testMethodName_FooThrowsIOException(); the generated test method will stub foo to throw an IOException when bar() called.

![Generate Test Method Gif](https://squaretest.com/images/gifs/V1_5/CreateTestMethods.gif)

## Create a Test Method
Start typing the name of the test method you want to create to see code completion suggestions based on the methods in your source class; then select one of the suggested methods to create it.

![Generate Test Method Gif](https://squaretest.com/images/gifs/V1_5/CreateTestMethod.gif)

## Features

### Generate a Test Class for Your Java Class with One Action
Use the configurable keyboard shortcut: `ctrl+alt+K` on Windows and Linux or `cmd+shift+L` on OS X to generate a test class in either Java (1.7+) or Groovy.

### The Generated Test Class Contains Appropriate Boilerplate Code

Squaretest automatically generates the following based on your source class:
1. Code to construct the source class and initialize its dependencies, in many cases
2. A test method for each public and package-local method in the source class, containing
   * Local variables for arguments required by the method
   * Mockito stubs; i.e. when() and doAnswer() statements
   * Code to invoke the method
   * An assertEquals() call or Groovy assertion statement
   * Mockito verify() statements
3. A test method for each exception declared by the corresponding source method
4. A test method for each exception thrown by each dependency that corresponding source method interacts with

### Choose Which Dependencies Should Be Mocked
Use configurable shortcut `alt+K` on Windows and Linux or `cmd+shift+J` on OS X to show a dialog asking to confirm which dependencies should be
mocked before generating the test class.

### Use a Velocity Template to Configure How the Test Classes Are Generated
All aspects of the generated test classes are determined by an Apache Velocity template. You can use one of the default templates included with Squaretest or create your own template. [More...](https://squaretest.com#user_guide_create_template)

### Includes Default Velocity Templates for Common Test Frameworks
Squaretest includes default Velocity templates for both Java and Groovy for the following test frameworks and mocking frameworks.

* JUnit4 with Mockito
* JUnit5 with Mockito
* TestNG with Mockito
* Robolectric3 with Mockito
* AndroidJUnit4 with Mockito

### Detects Design Patterns in the Source Class
All templates included with Squaretest detect and handle the following design patterns in the source class.
* Class with dependencies provided in the constructor (the standard Java component)
* Class with private, dependency-annotated fields (fields annotated with @Inject or @Autowired)
* Class with package-local, dependency-annotated fields (common in Android apps using [Dagger](https://google.github.io/dagger/) or [Guice](https://github.com/google/guice)).
* Class with static creator methods like parse(..) or from(..) but no package-visible constructor; i.e. the sealed abstract class and similar patterns
* Abstract classes in general
* Class containing only static methods; i.e. the Utils classes.
* Singleton (traditional and enum)
* Enums in general
* Android activity (Robolectric3 and AndroidJUnit4 templates only)

### Quick Settings in the Default Templates
All templates included with Squaretest have Quick Settings, or variables set at the top of the file that can be used to set code style and other settings. The Quick Settings include the following options.

* Use special prefixes for test class members containing dependencies and/or mocks
* Use special prefixes for local fields containing test-method parameters and/or mocks
* Customize the name of the member or local field used to store the instance of the source class.
* Use static imports for initMocks and related methods
* Use mocks for mockable parameters whose names end in listener or callback
* Use custom initialization expressions for dependencies and test method arguments of certain types. [More...](https://squaretest.com/#template_api_quick_settings)

### Default Values for Common Types
Squaretest uses default values for certain commonly-used types that you usually don't want to mock; e.g. dependencies and local fields of type List will be set to Arrays.asList() instead of a mock or null. Squaretest recognizes 150+ default types from the following libraries.

* The JDK 8
* Apache Commons Lang 2 and 3
* Google Guava
* RxJava

Squaretest also selects default values based on the libraries present on the test classpath; e.g. dependencies and local fields of type Executor will use MoreExecutors.directExecutor() from Google Guava if it's available on the test classpath.

Similarly, Squaretest also generates doAnswer() statements for dependency interactions that take in Callables or Runnables; these statements invoke the call() or run() methods and return either a CompletableFuture or ListenableFuture (when applicable).

### Configurable Project-Level and Module-Level Settings

* Configure which Velocity template will be used to create your unit tests in the [Project Settings](https://squaretest.com/#user_guide_project_settings) and/or [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Configure where Squaretest will save the generated tests in the [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Squaretest can configure your [Module Settings](https://squaretest.com/#user_guide_module_settings) automatically in many cases; see [Module Configuration](https://squaretest.com/#user_guide_module_config) for more.

## Support
Please feel free track bugs here and post feedback, questions and feature requests to the [forum](https://squaretest.com/#forum).

## Terms and Conditions
By installing and using Squaretest, you agree to the [Terms and Conditions](https://squaretest.com/docs/eula-terms.html).
