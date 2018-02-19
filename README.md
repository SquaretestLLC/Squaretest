# Squaretest - for IntelliJ IDEA

The Squaretest plugin for IntelliJ IDEA allows you to automatically generate a test-class with appropriate boilerplate code for your Java class with one keyboard shortcut.
The plugin is currently in beta.
## Quick Start
Use Squaretest to create a test-class for your Java class by following the instructions below
1. Open the Java class
2. Do one of the following
   * Press `ctrl+alt+k` on Windows/Linux or `cmd+shift+k` on OS X
   * Choose **Squaretest | Generate Test** from the toolbar
3. Follow the instructions on the **Configure Squaretest Module Settings** dialog.

Note that if Squaretest finds an existing test-class with the same canonical name as the generated test-class, it will open the existing test-class instead of saving the generated one.

## Features

### Generate a test-class for your Java class with one action
Use the configurable keyboard shortcut: `ctrl+alt+k` on Windows and Linux or `cmd+shift+k` on OS X to generate a test-class in either Java (1.7+) or Groovy.

### The generated test-class contains appropriate boilerplate code 
Squaretest automatically generates the following based on your source-class:

1. Code to construct the instance of the source-class and initialize its dependencies, in many cases
2. A test-method for each public and package-local method in the source-class, containing
   * Local-variables for the arguments required by the method
   * A local-variable for the expected returned-value
   * Code to invoke the method
   * An assertEquals() call or Groovy assertion statement to verify the returned value

### Use a Velocity template to configure how the test-classes are generated
All aspects of the generated test-classes are determined by an Apache Velocity template. You can use one of the default templates included with Squaretest or create your own template. [More...](https://squaretest.com#user_guide_create_template)

### Includes default Velocity templates for common test frameworks
Squaretest includes default Velocity templates for both Java and Groovy for the following test-frameworks and mocking frameworks.

* JUnit4 with Mockito
* JUnit5 with Mockito
* Robolectric3 with Mockito
* AndroidJUnit4 with Mockito

### Robust default templates
All templates included with Squaretest detect and handle the following code-patterns in the source-class.
* Class with dependencies provided in the constructor (the standard Java component)
* Class with private, dependency-annotated fields (fields annotated with @Inject or @Autowired)
* Class with package-local, dependency-annotated fields (common in Android apps using [Dagger](https://google.github.io/dagger/) or [Guice](https://github.com/google/guice)).
* Class with static creator methods like parse(..) or from(..) but no package-visible constructor; i.e. the sealed abstract class and similar patterns
* Abstract classes in general
* Class containing only static methods; i.e. the Utils classes.
* Singleton (traditional and enum)
* Enums in general
* Android activity (Robolectric3 and AndroidJUnit4 templates only)

### Quick Settings in the default templates
All templates included with Squaretest have Quick Settings, or variables set at the top of the file that can be used to set code-style and other settings. The Quick Settings include the following options.

* Use special prefixes for test-class members containing dependencies and/or mocks
* Use special prefixes for local-fields containing test-method parameters and/or mocks
* Customize the name of the member or local-field used to store the instance of the source class.
* Use static imports for initMocks and related methods
* Use mocks for mockable parameters whose names end in listener or callback
* Use custom initialization expressions for dependencies and test-method arguments of certain types. [More...](https://squaretest.com/#template_api_quick_settings)

### Default values for common types
Squaretest uses default values for certain commonly-used types that you usually don't want to mock; e.g. dependencies and local-fields of type List will be set to Arrays.asList() instead of a mock or null. Squaretest recognizes 150+ default types from the following libraries.

* The JDK 8
* Apache Commons Lang 2 and 3
* Google Guava
* RxJava

Squaretest also selects default-values based on the libraries present on the test classpath; e.g. dependencies and local-fields of type Executor will use MoreExecutors.directExecutor() from Google Guava if it's available on the test classpath.

### Configurable Project-Level and Module-Level Settings

* Configure which Velocity template will be used to create your unit-tests in the [Project Settings](https://squaretest.com/#user_guide_project_settings) and/or [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Configure where Squaretest will save the generated tests in the [Module Settings](https://squaretest.com/#user_guide_module_settings).
* Squaretest can configure your [Module Settings](https://squaretest.com/#user_guide_module_settings) automatically in many cases; see [Module Configuration](https://squaretest.com/#user_guide_module_config) for more.

### Open existing unit-tests with the same keyboard shortcut 
Before saving the generated test-class, Squaretest searches the project for a class with the same or similar canonical-name. If a match is found, Squaretest opens the existing test instead of creating a new one. Similar canonical-name means the canonical-name +/- 's'; e.g. if the test-class is named com.myapp.FooTest, the similar name is com.myapp.FooTests and vice versa.
This allows you to jump from a source class to its unit-tests with one keyboard shortcut.

### Configure where the unit-test file is opened
Squaretest can open the generated (or existing) unit-test file in the following ways, configured by the [Application Settings](https://squaretest.com/#user_guide_application_settings).

* In the same editor window as the source class.
* In the editor window next to the source class if one is available.
* In an editor window next to the source class, splitting the editor-window containing the source class vertically to create one if needed.

## Support 
Please feel free track bugs here and post feedback, questions and feature-requests to the [support forum](https://squaretest.com#support).

## Terms and Conditions
By installing and using Squaretest, you agree to the [Terms and Conditions](https://squaretest.com/docs/eula-terms.html).