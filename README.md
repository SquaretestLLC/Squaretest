# Squaretest Java Unit Test Generator for IntelliJ IDEA

The Squaretest plugin for IntelliJ IDEA allows you to automatically generate unit tests for your Java source classes.
The generated test classes contain code to construct the instance of the source class (if needed) and initialize the
dependencies to either mocks or reasonable default values. The test classes include test methods that invoke their corresponding source methods
and compare the returned values to expected values. The tests also include Mockito stubs; i.e. when(), doAnswer() and verify() statements.
Squaretest also generates tests for alternate flows.

## Create a Test Class for a Java Class
Select **Squaretest | Generate Test - Confirm Options** (Alt+Insert+Generate Test - Confirm Options) to create a test class for your Java source class.

![Generate Test Gif](https://github.com/SquaretestLLC/Squaretest/assets/31743679/261de96b-ef52-459a-88e5-e795c13a0be2)

## Create Test Methods
Select **Squaretest | Generate Test Methods** (Alt+Insert+Generate Test Methods) to see a list of test methods to add to your test class.
The list includes tests for alternate flows for each source method; e.g. if a source method calls foo.bar(), and bar() can throw an IOException, Squaretest will suggest a method called testMethodName_FooThrowsIOException(); the generated test method will stub foo to throw an IOException when bar() called.

![Create Test Method Gif](https://github.com/SquaretestLLC/Squaretest/assets/31743679/3b914d62-ff33-48e7-a1aa-a3118889ce56)

## Features

### Generate a Test Class for Your Java Class with One Action
Select **Squaretest | Generate Test** (Alt+Insert+Generate Test) to generate a test class in either Java (1.8+) or Groovy.

### The Generated Test Class Contains Appropriate Boilerplate Code

Squaretest automatically generates the following based on your source class:
1. Code to construct the source class and initialize its dependencies, when appropriate
2. A test method for each public and package-local method in the source class, containing
   * Local variables for arguments required by the method
   * Mockito stubs; i.e. when() and doAnswer() statements
   * Code to invoke the method
   * An assertEquals() call or Groovy assertion statement
   * Mockito verify() statements
   * Code to initialize data transfer object (DTO) beans used in the above
3. Test methods for alternate flows, including cases where:
   * Dependency methods throw exceptions
   * Dependency methods return either null or absent values; e.g. Optional.absent()
   * Dependency methods return empty values; e.g. Collections.emptyList()
   * Dependency methods return failure values; e.g. CompletableFuture.failedFuture(exception)
   * Dependency methods return broken I/O values; e.g. new BrokenInputStream()
   * Dependency methods return empty I/O values; e.g. InputStream.nullInputStream()
   * Method parameters contain broken I/O values; e.g. new BrokenInputStream()

### Choose Which Dependencies Should Be Mocked
Select **Squaretest | Generate Test - Confirm Options** (Alt+Insert+Generate Test - Confirm Options) to choose the following options before creating the test class:
* Which dependencies should be mocked
* Which methods should be tested
* Whether to construct the source class in a setup method or in each test method.

### Uses Dataflow Analysis and Control Flow Analysis
Squaretest uses dataflow analysis and control flow analysis to generate as much of the tests as it can. In many cases developers only need to fill in the details in order to complete the tests. For example change the placeholder values, add assertions, etc.

### Reads the Javadocs
Squaretest reads the Javadocs for the dependency methods the source class interacts with. This enables Squaretest to create tests for cases where:
* The dependency methods throw the exceptions declared in the @throws tags
* The dependency methods return null, in some cases
  * Squaretest looks for annotations indicating whether or not a method can return null.
  * Squaretest uses very basic Natural Language Processing (NLP) to determine if the Javadocs indicate a method can return null.
This enables Squaretest to create tests for cases the developer may have missed due to not reading the Javadocs.

### Use a Velocity Template to Configure How the Test Classes Are Generated
All aspects of the generated test classes are determined by an Apache Velocity template. You can use one of the default templates included with Squaretest or create your own template. [More...](https://squaretest.com#user_guide_create_template)

### Includes Default Velocity Templates for Common Test Frameworks
Squaretest includes default Velocity templates for both Java and Groovy for the following test frameworks and mocking frameworks.

* JUnit4 with Mockito
* JUnit4 with Mockito and AssertJ
* JUnit4 with Spring and Mockito
* JUnit4 with Spring, Mockito and AssertJ
* JUnit5 with Mockito
* JUnit5 with Mockito and AssertJ
* JUnit5 with Spring and Mockito
* JUnit5 with Spring, Mockito and AssertJ
* TestNG with Mockito
* Robolectric3+ with Mockito
* AndroidJUnit4 with Mockito

### Detects Design Patterns in the Source Class
All templates included with Squaretest detect and handle the following design patterns in the source class.
* The standard Java component with dependencies provided in the constructor
* Components with private fields annotated with @Inject, @Autowired or similar annotations
* Components with package-local fields annotated with @Inject or similar annotations. These are common in Android apps using Dagger or Guice).
* Components with dependencies provided via setter methods.
* Classes with static factory methods like parse(..) or from(..) but no package-visible constructor; i.e. the sealed abstract class and similar patterns
* Abstract classes in general
* Classes containing only static methods; e.g. the Utils classes.
* Traditional Singletons
* Enum Singletons
* Enums in general
* Android activities (Robolectric3 and AndroidJUnit4 templates only)
* Spring controllers (Spring templates only)
* [More Examples](https://squaretest.com/#examples)

### Initializes Data Transfer Objects
Squaretest initializes data transfer objects (DTOs) returned by dependency methods as well as those used in method parameters. Squaretest recursively initializes DTOs using the following patterns.
* Classes with data provided via the constructor.
* Classes with data provided via lombok builder methods.
* Beans with data provided via setter methods.
* JAXB beans with list properties (where data is provided by adding items to the list returned by the corresponding getter method).
* Classes generated by Protobuf-3
* Request and Response classes used in the AWS SDK V1 and V2.
* Classes initialized by calling static factory methods like from(..) or of(..).

### Generates Code Based on Dependency Method Return Values
Squaretest generates code based on the values returned by dependency methods the source methods interact with. This includes the following.
* Verify statements to ensure Closeables returned by dependency methods are closed.
* Alternate flow tests for some AWS SDK APIs that return absent or empty values.
* Alternate flow tests for dependency methods that return objects containing I/O values; e.g. the AWS SDK S3 APIs for retrieving objects.

### Quick Settings in the Default Templates
All templates included with Squaretest have Quick Settings, or variables set at the top of the file that can be used to set code style and other settings. The Quick Settings include the following options.
* Use special prefixes for test class members containing dependencies and/or mocks
* Use special prefixes for local variables containing test method parameters and/or mocks
* Customize the name of the member or local field used to store the instance of the source class.
* Use the JUnit5 Mockito Extension or the JUnit4 Mockito Runner
* Use Mockito BDD
* Use static imports for initMocks and related methods
* Use mocks for mockable parameters whose names end in listener or callback
* Use custom initialization expressions for dependencies and test method arguments of certain types. [More...](https://squaretest.com/#template_api_quick_settings)

### Default Values for Common Types
Squaretest uses default values for commonly-used types that developers don't usually mock; e.g. dependencies and local variables of type List will be set to either Arrays.asList() or List.of() instead of a mock or null. Squaretest recognizes 500+ default types from the JDK and various open source libraries, including.
* The JDK 8+
* Apache Commons Lang 2 and 3
* Google Guava
* RxJava
* Retrofit
* AWS SDK V1 and V2
* The Spring Framework

Squaretest also selects default values based on the libraries present on the test classpath. For example dependencies and local variables of type Executor will use MoreExecutors.directExecutor() from Google Guava if it is available on the test classpath.

Similarly, Squaretest generates doAnswer() statements for dependency interactions that take in Callables or Runnables. These statements invoke the call() or run() methods and return either a CompletableFuture or ListenableFuture when applicable.

### Configurable Project-Level and Module-Level Settings

* Configure which Velocity template will be used to create your unit tests in the [Project Settings](https://squaretest.com/#user_guide_project_settings) and/or [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Configure where Squaretest will save the generated tests in the [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Squaretest can configure your [Module Settings](https://squaretest.com/#user_guide_module_settings) automatically in many cases; see [Module Configuration](https://squaretest.com/#user_guide_module_config) for more.

## Support
Please feel free track bugs here and post feedback, questions and feature requests in the [Discussions](https://github.com/SquaretestLLC/Squaretest/discussions).

## Terms and Conditions
By installing and using Squaretest, you agree to the [Terms and Conditions](https://squaretest.com/docs/eula-terms.html).
